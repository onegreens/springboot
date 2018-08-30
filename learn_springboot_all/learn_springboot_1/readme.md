# 整合quartz

## 添加依赖
```xml
<dependency>
    <groupId>org.quartz-scheduler</groupId>
    <artifactId>quartz</artifactId>
    <version>1.8.4</version>
</dependency>
 <dependency><!-- 该依赖必加，里面有spring对schedule的支持 -->
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
        </dependency>
        <dependency><!-- 事务配置 不知道怎么需要这个 -->
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
```

##添加配置类 

SchedledConfiguration