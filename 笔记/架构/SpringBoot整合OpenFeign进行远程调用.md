# SpringBoot整合OpenFeign进行远程调用
## 1. 引入依赖
引入OpenFeign依赖
```xml
        <!--openfeign-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
```

修改nacos-discovery依赖
```xml
        <!--nacos-discovery-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.cloud</groupId>
                    <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
```

由于SpringCloud Feign在Hoxton.M2 RELEASED版本之后不再使用Ribbon而是使用spring-cloud-loadbalancer，所以不引入spring-cloud-loadbalancer会报错。



在nacos中排除ribbon依赖，不然loadbalancer无效。



引入loadbalancer依赖

```xml
        <!--loadbalancer-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-loadbalancer</artifactId>
        </dependency>
```

## 2. 配置Feign扫描

在启动类``**Apllication.java``中，配置注解``@EnableFeignClients``

```java
@MapperScan("com.learn.project.mall.member.dao")
@EnableFeignClients(basePackages = "com.learn.project.mall.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class MallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallMemberApplication.class, args);
    }

}
```

- basePackages: 用于指定扫描的包路径

## 3. 被调用方

正常编写Controller即可

```java
@RestController
@RequestMapping("coupon/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @RequestMapping("member/list")
    public R membercoupon() {
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponName("满100-50");
        return R.ok().put("coupons", couponEntity);
    }
}
```



## 4. 调用方

### 新建一个``feign``包

用于存放需要调用 远程服务的 服务。

### 新建Feign类

```java
/**
 * Coupon远程服务
 *
 * @author chenfuyuan
 * @date 2021/12/8 16:13
 */
@FeignClient("coupon")
public interface CouponFeignService {

    @RequestMapping("/coupon/coupon/member/list")
    public R membercoupon();


}
```

- 需要在类上添加注解``@FeignClient``
- 方法上要添加``@RequestMapping``注解 指明要调用的服务。其中填写调用服务的全路径。



### 调用Feign

```java
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private CouponFeignService couponFeignService;

    @RequestMapping("/test")
    public R test(){
        MemberEntity member = new MemberEntity();
        member.setNickname("张三");
        R getMemberCouponsResult = couponFeignService.membercoupons();
        return R.ok().put("member", member).put("coupons", getMemberCouponsResult.get("coupons"));
    }
}
```



