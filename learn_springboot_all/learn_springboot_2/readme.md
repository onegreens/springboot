# learn_springboot_2

利用idea创建

## Spring Boot属性配置文件详解

### 1. 自定义属性

在application.properties中编写

```properties
  com.name = chen
  com.age = 24
```

测试参考：UserPoTest


### 2. 参数间的引用

```properties
com.name = chen
com.age = 24
#属性间的引用
com.info = \u59d3\u540d ${com.name}，年龄：${com.age}
#中文字符可能会导致乱码 可使用ASCII
```

### 3.随机数
```properties
# 随机字符串
com.didispace.blog.value=${random.value}
# 随机int
com.didispace.blog.number=${random.int}
# 随机long
com.didispace.blog.bignumber=${random.long}
# 10以内的随机数
com.didispace.blog.test1=${random.int(10)}
# 10-20的随机数
com.didispace.blog.test2=${random.int[10,20]}

```

### 4. 加载配置文件
```properties
spring.profiles.active=dev
```



## 添加Swagger2

参考learn_springboot_2

### 1.添加依赖

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.2.2</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.2.2</version>
</dependency>
```
### 2.创建Swagger2配置类

在`Application.java`同级创建Swagger2的配置类`Swagger2`。 