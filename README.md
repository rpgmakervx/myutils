# myutils
myutils是一个开源工具类合集，目前包括myutils-lang,myutils-db两部分。lang模块包括io,文件,编解码,字符串,数字,数组,反射等工具类；
db模块主要包括数据库连接池和数据库操作工具类两部分。

## 起步：
clone项目后，在你希望使用的模块，用`mvn install`将项目置入你的本地仓库.例如你希望使用`myutils-db`，则操作如下：

`cd workspace/myutils/myutils-db`

`mvn install`

然后在你的项目中引入myutils模块即可：

```xml
<dependency>
    <groupId>org.easyArch</groupId>
    <artifactId>myutils-db</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
