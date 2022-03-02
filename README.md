# SpringMVC
Spring MVC notebook

## 知识框架
| 内容 | 说明 | 重要程度 |
| --- | --- | ---|
|Spring MVC|开发流程和环境配置|✨✨✨✨✨|
|接受web数据|参数接受与数据绑定|✨✨✨✨✨|
|URL Mapping|讲解URL绑定过程|✨✨✨✨✨|
|中文乱码问题|解决请求与响应中文乱码|✨✨✨✨✨|
|拦截器|拦截器使用方法|✨✨✨|
|Restful风格|规范|✨✨✨✨✨|
|Restful应用|实例结合Restful与Spring MVC|✨✨✨✨✨|
|Json序列号|通过响应输出数据|✨✨✨✨✨|
|Restful跨域问题|分析问题来源与解决办法|✨✨✨✨✨|

使用方法同[Spring](https://github.com/Leekinghou/Spring)的使用方法教程

## 目录
- [s01](#SpringMVC项目初始化)SpringMVC项目基本配置、各个xml书写
- [s02](#URLMapping映射)URLMapping映射、前后端变量注入
- [s03](#JavaBean方式实现对象注入)JavaBean方式实现对象注入
- [s04](#实例实现SpringMVC) 接收表单数据（列表、集合方式）、关联对象赋值、日期类型转换、自定义转换器
# SpringMVC项目初始化
MVC是一种架构模式
![](https://gitee.com/leekinghou/image/raw/master/img/20220226110238.png)
核心是Controller控制器，用于处理请求，产生响应  

Spring MVC基于Spring IoC容器，所有对象被IoC容器管理  
pom.xml:
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.2.19.RELEASE</version>
    </dependency>
</dependencies>
```

web.xml配置:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">

    <!--    DispatchServlet-->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <!--
        DispatcherServlet是Spring MVC最核心的对象
        DispatcherServlet用于拦截HTTP请求
        并根据请求的URL调用与之对应的Controller方法，来完成Http请求的处理
        -->
        <!-- 相当于公司前台，负责接待 -->
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!-- 只要是spring就离不来applicationContext.xml -->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:applicationContext.xml</param-value>
        </init-param>
        <!--
        在web应用启动时自动创建Spring IOC容器，
        并初始化DispatcherServlet
        -->
        <load-on-startup>0</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <!-- "/"代表拦截所有的请求 -->
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```

applicationContext.xml:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/mvc
        https://www.springframework.org/schema/mvc/spring-mvc.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
    <!--
    context:component-scan 标签作用：
    在Spring IoC初始化过程中，自动创建并且管理com.springmvc及子包
    拥有一下注解的对象：
    1. @Repository
    2. @Service
    3. @Controller
    4. @Component
    -->
    <context:component-scan base-package="com.springmvc"/>
    <!--  启用Spring MVC的注解开发模式  -->
    <mvc:annotation-driven/>
    <!--  将图片/JS/CSS等静态资源排除在外(不被DispatcherServlet拦截)，可以提高效率  -->
    <mvc:default-servlet-handler/>
</beans>
```

控制器配置:
```java
@Controller
public class TestController {
    @GetMapping("/test")
    @ResponseBody // 直接向响应输出字符串数据，不跳转页面
    public String test(){
    return "Hello Spring MVC";
    }
}
```
浏览器访问响应流程图：
![](https://gitee.com/leekinghou/image/raw/master/img/1645871292034.png)


# URLMapping映射
前端变量名和后端变量名不一致时的映射方法
```java
@GetMapping("/g")
@ResponseBody
public String getMapping(@RequestParam("manager_name") String managerName){
    System.out.println("manager:" + managerName);
    return "This is a get method";
}
```

# JavaBean方式实现对象注入
```java
public class User {
    private String username;
    private Long password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPassword() {
        return password;
    }

    public void setPassword(Long password) {
        this.password = password;
    }
}
```

```java
@Controller
//@RequestMapping("/test")
public class URLMappingController {
    @PostMapping("/p1")
    @ResponseBody
    public String postMapping1(User user){
        System.out.println(user.getUsername() + ":" + user.getPassword());
        return "This is post method";
    }
}
```

# 实例实现SpringMVC
一个调查问卷实例
## URI 绝对路径与相对路径
![](https://gitee.com/leekinghou/image/raw/master/img/20220301222649.png)
![](https://gitee.com/leekinghou/image/raw/master/img/20220301223044.png)

- 接收表单数据（列表、集合方式）
- `@RequestParam`
```java
@PostMapping("/apply")
@ResponseBody
public String apply(String name, String course, @RequestParam List<Integer> purpose){
    return "SUCCESS";
}
```

- 关联对象赋值
```java
public class Form {
    private Delivery delivery = new Delivery();
    
    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
```
控制器调用：
```java
public String apply(Form form){
    return "SUCCESS";
}
```

- 日期类型转换、自定义转换器
方法一:`@DateTimeFormat`
```java
    /**
     * 时间格式转换
     */
    @PostMapping("/apply")
    @ResponseBody
    public String apply(String name, String course, @DateTimeFormat(pattern = "yyyy-MM-dd") Date createTime){
        System.out.println(name);
        System.out.println(course);
        return "SUCCESS";
    }
```

方法二：
```java
public class Form {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
```
调用：
```java
    /**
     * 时间格式转换
     */
    @PostMapping("/apply")
    @ResponseBody
    public String apply(String name, String course, Date createTime){
        System.out.println(name);
        System.out.println(course);
        return "SUCCESS";
    }
```

方法三：自定义类，使用bean
```xml
<!--  启用Spring MVC的注解开发模式  -->
<mvc:annotation-driven conversion-service="conversionService"/>

<bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
    <property name="converters">
        <set>
            <bean class="com.springmvc.converter.MyDateConverter"/>
        </set>
    </property>
</bean>
```

实践类：  
实际情况中，要对多种类型的数据进行判断
```java
public class MyDateConverter implements Converter<String, Date> {
    public Date convert(String s){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = sdf.parse(s);
            return parse;
        } catch (ParseException e) {
            return null;
        }
    }
}
```

调用:
```java
/**
 * 自定义转换器实现时间格式转换
 */
@PostMapping("/apply")
@ResponseBody
public String apply(String name, String course, Date createTime){
    System.out.println(name);
    System.out.println(course);
    return "SUCCESS";
}
```
