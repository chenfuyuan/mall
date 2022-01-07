# SpringBoot整合阿里云OSS对象存储服务
## 开通阿里云OSS对象存储服务
登录阿里云控制台，创建``Bucket``

![image-20220106140429458](https://chenfuyuan-markdown-img.oss-cn-shenzhen.aliyuncs.com/20220106140431.png)

## 创建子AccessKey

创建子AccessKey，专门用于Oss对象存储权限分配。与主AccessKey进行权限隔离。

![image-20220106141052107](https://chenfuyuan-markdown-img.oss-cn-shenzhen.aliyuncs.com/20220106141053.png)



![image-20220106141130638](https://chenfuyuan-markdown-img.oss-cn-shenzhen.aliyuncs.com/20220106141131.png)

勾选Open API调用访问方式。允许使用代码调用API进行访问。



![image-20220106141358993](https://chenfuyuan-markdown-img.oss-cn-shenzhen.aliyuncs.com/20220106141403.png)

添加权限策略``AliyunOSSFullAccess``，允许管理对象存储服务OSS所有权限



## 集成阿里云OSS

参考文档[https://github.com/alibaba/aliyun-spring-boot/tree/master/aliyun-spring-boot-samples/aliyun-oss-spring-boot-sample](https://github.com/alibaba/aliyun-spring-boot/tree/master/aliyun-spring-boot-samples/aliyun-oss-spring-boot-sample)

### 1. 引入依赖

```xml
<!--引入阿里云oss spring启动器-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>aliyun-oss-spring-boot-starter</artifactId>
</dependency>

<!--引入阿里云 依赖版本管理-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>aliyun-spring-boot-dependencies</artifactId>
    <version>${aliyun-spring-boot-dependencies.version}</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>
```

### 获取签名后直接上传

阿里云官方帮助[https://help.aliyun.com/document_detail/31926.htm?spm=a2c4g.11186623.0.0.432b566aAitze3#concept-en4-sjy-5db](https://help.aliyun.com/document_detail/31926.htm?spm=a2c4g.11186623.0.0.432b566aAitze3#concept-en4-sjy-5db)

```java
@Service
public class AliYunOssServiceImpl implements OssService {
    @Autowired
    OSS ossClient;

    @Autowired
    AliYunOssConfig aliYunOssConfig;
    @Override
    public AliYunOssSignature getSignature() {
        AliYunOssSignature result = new AliYunOssSignature();
        try {
            String dir = TimeUtil.todayNoTimeToStr() + "/";

            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, aliYunOssConfig.getContentLengthRange());
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            //获取超时时间
            Date expiration = aliYunOssConfig.getExpiration();
            //根据AliYunOss配置生成策略
            String postPolicy = ossClient.generatePostPolicy(expiration,policyConds);
            //根据策略生成签名
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            //进行封装

            byte[] binaryData = postPolicy.getBytes("utf-8");
            //进行Base64编码
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            result.setPolicy(encodedPolicy);
            result.setDir(dir);
            result.setAccessId(aliYunOssConfig.getAccessId());
            result.setHost(aliYunOssConfig.getHost());
            result.setExpire(String.valueOf(expiration.getTime() / 1000));
            result.setSignature(postSignature);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ossClient.shutdown();
        }
    }
}


@Component
@Data
public class AliYunOssConfig {
    /**
     * access-key
     */
    @Value("${alibaba.cloud.access-key}")
    private String accessId;

    /**
     * secret-key
     */
    @Value("${alibaba.cloud.secret-key}")
    private String accessKey;

    @Value("${alibaba.cloud.oss.endpoint}")
    private String endpoint;

    @Value("${config.oss.bucket}")
    private String bucketName;

    @Value("http://${config.oss.bucket}.${alibaba.cloud.oss.endpoint}")
    private String host;

    /**
     * 超时时间(默认为30秒)
     */
    @Value("${config.oss.expire-time}")
    private long expireTime = 30;

    /**
     * 上传文件最大大小
     */
    @Value("${config.oss.content-length-range}")
    private long contentLengthRange = 1048576000;

    /**
     * 获取超时时间
     * @return
     */
    public Date getExpiration(){
        long expireEndTime = System.currentTimeMillis() + expireTime;
        Date expiration = new Date(expireEndTime);
        return expiration;
    }

    @Override
    public String toString() {
        return "AliYunOssConfig{" +
                "accessId='" + accessId + '\'' +
                ", accessKey='" + accessKey + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", bucketName='" + bucketName + '\'' +
                ", host='" + host + '\'' +
                ", expireTime=" + expireTime +
                ", contentLengthRange=" + contentLengthRange +
                '}';
    }
}

```

- ``OSSClient``为阿里云提供的OSS服务api
- ``AliYunOssConfig``对象，从配置文件中，读取相关的配置

#### 过程

![时序图](https://chenfuyuan-markdown-img.oss-cn-shenzhen.aliyuncs.com/20220106174610.png)

1. 用户根据OSS配置，构建``PolicyConditions``对象，设置策略、文件路径、最大提交文件大小。
2. 调用api方法根据``PolicyConditions``和``超时时间expiration``生成``PostPolicy``表单策略
3. 调用api方法，向服务器上传策略，获取对应的签名。
4. 将配置信息，策略，签名封装成对象传回前端。
5. 前端根据签名，直接上传文件到OSS服务器。



## 前端上传文件(以vue.js + elementUI)为例

policy.js

用于从后台获取签名

```js
import http from '@/utils/httpRequest.js'
export function policy () {
  return new Promise((resolve, reject) => {
    http({
      url: http.adornUrl('/thirdparty/oss/signature'),
      method: 'get',
      params: http.adornParams({})
    }).then(({ data }) => {
      resolve(data)
    })
  })
}

```

单文件上传组件singleUpload.vue

```vue
<template> 
  <div>
    <el-upload
      action="http://chenfuyuan-mall.oss-cn-shenzhen.aliyuncs.com"
      :data="dataObj"
      list-type="picture"
      :multiple="false" :show-file-list="showFileList"
      :file-list="fileList"
      :before-upload="beforeUpload"
      :on-remove="handleRemove"
      :on-success="handleUploadSuccess"
      :on-preview="handlePreview">
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过10MB</div>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="fileList[0].url" alt="">
    </el-dialog>
  </div>
</template>
<script>
   import {policy} from './policy'
   import { getUUID } from '@/utils'

  export default {
    name: 'singleUpload',
    props: {
      value: String
    },
    computed: {
      imageUrl() {
        return this.value;
      },
      imageName() {
        if (this.value != null && this.value !== '') {
          return this.value.substr(this.value.lastIndexOf("/") + 1);
        } else {
          return null;
        }
      },
      fileList() {
        return [{
          name: this.imageName,
          url: this.imageUrl
        }]
      },
      showFileList: {
        get: function () {
          return this.value !== null && this.value !== ''&& this.value!==undefined;
        },
        set: function (newValue) {
        }
      }
    },
    data() {
      return {
        dataObj: {
          policy: '',
          signature: '',
          key: '',
          ossaccessKeyId: '',
          dir: '',
          host: '',
          // callback:'',
        },
        dialogVisible: false
      };
    },
    methods: {
      emitInput(val) {
        this.$emit('input', val)
      },
      handleRemove(file, fileList) {
        this.emitInput('');
      },
      handlePreview(file) {
        this.dialogVisible = true;
      },
      beforeUpload(file) {
        let _self = this;
        return new Promise((resolve, reject) => {
          policy().then(response => {
            debugger;
            let signature = response.signature;
            _self.dataObj.policy = signature.policy;
            _self.dataObj.signature = signature.signature;
            _self.dataObj.ossaccessKeyId = signature.accessId;
            _self.dataObj.key = signature.dir + getUUID()+'_${filename}';
            _self.dataObj.dir = signature.dir;
            _self.dataObj.host = signature.host;
            resolve(true)
          }).catch(err => {
            reject(false)
          })
        })
      },
      handleUploadSuccess(res, file) {
        console.log("上传成功...")
        this.showFileList = true;
        this.fileList.pop();
        this.fileList.push({name: file.name, url: this.dataObj.host + '/' + this.dataObj.key.replace("${filename}",file.name) });
        this.emitInput(this.fileList[0].url);
      }
    }
  }
</script>
<style>

</style>
```

多文件上传组件multiUpload.vue

```vue
<template>
  <div>
    <el-upload
      action="http://chenfuyuan-mall.oss-cn-shenzhen.aliyuncs.com"
      :data="dataObj"
      list-type="picture-card"
      :file-list="fileList"
      :before-upload="beforeUpload"
      :on-remove="handleRemove"
      :on-success="handleUploadSuccess"
      :on-preview="handlePreview"
      :limit="maxCount"
      :on-exceed="handleExceed"
    >
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt />
    </el-dialog>
  </div>
</template>
<script>
import { policy } from "./policy";
import { getUUID } from '@/utils'
export default {
  name: "multiUpload",
  props: {
    //图片属性数组
    value: Array,
    //最大上传图片数量
    maxCount: {
      type: Number,
      default: 30
    }
  },
  data() {
    return {
      dataObj: {
        policy: "",
        signature: "",
        key: "",
        ossaccessKeyId: "",
        dir: "",
        host: "",
        uuid: ""
      },
      dialogVisible: false,
      dialogImageUrl: null
    };
  },
  computed: {
    fileList() {
      let fileList = [];
      for (let i = 0; i < this.value.length; i++) {
        fileList.push({ url: this.value[i] });
      }

      return fileList;
    }
  },
  mounted() {},
  methods: {
    emitInput(fileList) {
      let value = [];
      for (let i = 0; i < fileList.length; i++) {
        value.push(fileList[i].url);
      }
      this.$emit("input", value);
    },
    handleRemove(file, fileList) {
      this.emitInput(fileList);
    },
    handlePreview(file) {
      this.dialogVisible = true;
      this.dialogImageUrl = file.url;
    },
    beforeUpload(file) {
      let _self = this;
      return new Promise((resolve, reject) => {
        policy()
          .then(response => {
            console.log("这是什么${filename}");
            signature = response.signature;
            _self.dataObj.policy = signature.policy;
            _self.dataObj.signature = signature.signature;
            _self.dataObj.ossaccessKeyId = signature.accessId;
            _self.dataObj.key = signature.dir + getUUID() + "_${filename}";
            _self.dataObj.dir = signature.dir;
            _self.dataObj.host = signature.host;
            resolve(true);
          })
          .catch(err => {
            console.log("出错了...",err)
            reject(false);
          });
      });
    },
    handleUploadSuccess(res, file) {
      this.fileList.push({
        name: file.name,
        // url: this.dataObj.host + "/" + this.dataObj.dir + "/" + file.name； 替换${filename}为真正的文件名
        url: this.dataObj.host + "/" + this.dataObj.key.replace("${filename}",file.name)
      });
      this.emitInput(this.fileList);
    },
    handleExceed(files, fileList) {
      this.$message({
        message: "最多只能上传" + this.maxCount + "张图片",
        type: "warning",
        duration: 1000
      });
    }
  }
};
</script>
<style>
</style>
```

### 跨域问题解决

需要去阿里云控制台，配置OSS跨域访问

![image-20220107203702103](https://chenfuyuan-markdown-img.oss-cn-shenzhen.aliyuncs.com/20220107203703.png)

![image-20220107203749208](https://chenfuyuan-markdown-img.oss-cn-shenzhen.aliyuncs.com/20220107203750.png)
