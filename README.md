# SpringBoot 实现QQ邮箱登录注册 

## 1. 登录注册思路   
### 1.1. 思路
```text
注册：通过输入的邮箱发送验证码，校验前端传来的验证码是否和后台生成的一致，
若一致，将数据写入数据库，完成注册。  

登录：通过输入的邮箱查询密码，然后比较密码是否一致，一致就是登录成功。
```

### 1.2. 整个项目结构图
![]()

## 2. 准备
### 2.1. 开启邮箱POP3/SMTP服务
登录qq邮箱后，点击左上角设置，选择账户，如下图。   
![](./src/main/resources/static/image/bg/2-1.png) 
  
然后一直往下滑，看到如下图的POP3/SMTP服务，点击开启，应该会让帮定的手机号发个短信，
然后会收到一个授权码，一定要好好保存，在appliction.properties配置中会用到。

![](./src/main/resources/static/image/bg/2-2.png)  

### 2.2. 创建SpringBoot项目，jdk选择8
`pom.xml依赖`
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>
    <!--mybatis-->
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>1.3.2</version>
    </dependency>

    <!--jdbc-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.19</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
        <exclusions>
            <exclusion>
                <groupId>org.junit.vintage</groupId>
                <artifactId>junit-vintage-engine</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
</dependencies>
```

### 2.3. application.properties 配置文件
```yaml
server:
  port: 8089

# 邮箱配置
spring:
  mail:
    host: smtp.qq.com  # 平台地址，这里使用的是qq邮箱
    username: xxxxx.test@qq.com
    password: xxxxxx
    properties:
      mail:
        smtp:
          ssl:
            enable: true
    default-encoding: utf-8
  datasource:  # 数据库相关配置
    url: jdbc:mysql//localhost:3306/emailSignUp?useSSL=true&characterEncoding=utf-8&serverTimezone=GMT
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: root
mybatis:   #配置mapper
  mapper-locations: classpath:mapper/*.xml
```