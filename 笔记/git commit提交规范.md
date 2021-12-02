# git commit提交规范
遵循 [Angular提交准则](https://github.com/angular/angular/blob/master/CONTRIBUTING.md#commit)

## 格式为:
```text
<type>[scope]: <subject>

[body]

[footer]
```

### type 类型(必填):
用于指定commit类型
```text
feat：新功能（feature）。

fix/to：修复bug，可以是QA发现的BUG，也可以是研发自己发现的BUG。

-   fix：产生diff并自动修复此问题。适合于一次提交直接修复问题
-   to：只产生diff不自动修复此问题。适合于多次提交。最终修复问题提交时使用fix

docs：文档（documentation）。

style：格式（不影响代码运行的变动）。

refactor：重构（即不是新增功能，也不是修改bug的代码变动）。

perf：优化相关，比如提升性能、体验。

test：增加测试。

chore：构建过程或辅助工具的变动。

revert：回滚到上一个版本。

merge：代码合并。

sync：同步主线或分支的Bug。
```

### scope 作用域(必填):
用于描述改动的范围（即改动的模块名）。如果一次commit修改多个模块，建议拆分成多次commit，以便更好追踪和维护。

### subject 简单描述(必填):
用一句话清楚的描述这次提交做了什么。不超过50个字符，结尾不加句号或其他标点符号。
``Summary in present tense. Not capitalized. No period at the end.``

如:
```text
fix(DAO):用户查询缺少username属性 
feat(Controller):用户查询接口开发
```

### body 正文(选填):
补充 subject，适当增加原因、目的等相关因素，也可不写。

### footer(选填):
出现以下两种情况时，需要填写:
-  **不兼容变动**
	
	 以BREAKING CHANGE开头，后面是变动的描述、变动的理由以及迁移的方法
	
	什么叫不兼容变动，比如用户密码的加密方式发生改变

-   **关闭issue**

       当前提交修改了某个issue
	   
```text
feature(数据层): 简短描述

详细描述

BREAKING CHANGE: 不兼容变动

Closes 关闭issue
``` 

	  

