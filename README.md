# myutils
myutils是一个开源工具类合集，目前包括myutils-lang,myutils-db,myutils-http,myutils-export。
* lang模块包括io,文件,编解码,字符串,json解析,数字,数组,反射等工具类；
* db模块主要包括数据库连接池和orm框架两部分；
* export模块目前只有将实体以excel形式导出功能；
* http模块是netty实现的httpclient工具，用户可在发送完请求后，非阻塞的执行其他任务，当希望获取响应的时候再获取结果

## 起步：
clone项目后，在你希望使用的模块，用`mvn install`将项目置入你的本地仓库.例如你希望使用`myutils-db`，则操作如下：

`cd workspace/myutils/myutils-db`

`mvn install`

然后在你的项目中引入myutils模块即可：

```xml
<dependency>
    <groupId>org.easyarch</groupId>
    <artifactId>myutils-db</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
