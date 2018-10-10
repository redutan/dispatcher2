# DispatcherServlet의 요청 처리 프로세스

## DispatcherServlet 소스 들여다보기

[https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java)

## DispatcherServlet의 요청 처리

## bootstrap

* DispatcherServlet static initialization block : 기본 전략 설정 파일 로딩
    * [https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L277](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L277)
* DispatcherServlet.properties : 기본 전략 설정 파일
    * [https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/resources/org/springframework/web/servlet/DispatcherServlet.properties](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/resources/org/springframework/web/servlet/DispatcherServlet.properties)
* HttpServletBean.init()
    * [https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/HttpServletBean.java#L147](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/HttpServletBean.java#L147)
* FrameworkServlet.initServletBean()
    * [https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L486](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L486)
* FrameworkServlet.initWebApplicationContext()
    * [https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L522](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L522)
* @MVC
    * AnnotationDrivenBeanDefinitionParser: `<mvc: annotation-driven />`
        * [https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/config/AnnotationDrivenBeanDefinitionParser.java](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/config/AnnotationDrivenBeanDefinitionParser.java)
        * MvcNamespaceUtils.registerDefaultComponents()
            * `<mvc:resources />` 사용 시 `@Controller` 설정이 먹지 않는 오류를 해결하기 위해
            BeanNameUrlHandlerMapping, HttpRequestHandlerAdapter, SimpleControllerHandlerAdapter 를 추가 등록
            * [https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/config/MvcNamespaceUtils.java#L63](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/config/MvcNamespaceUtils.java#L63)
    * WebMvcConfigurationSupport: `@EnableWebMvc`
        * [https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/config/annotation/WebMvcConfigurationSupport.java](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/config/annotation/WebMvcConfigurationSupport.java)
* FrameworkServlet.configureAndRefreshWebApplicationContext()
    * [https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L639](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L639)
    * cf.) ApplicationContext 와 WebApplicationContext의 차이는?
* DispatcherServlet.onRefresh() -> DispatcherServlet.initStrategies()
    * [https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L487](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L487)
* initialize each strategies ...

## 요청 선처리 작업

* FrameworkServlet.service() -> HttpServlet.doGet()/doPost()/... -> FrameworkServlet.processRequest()
    * [https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#838](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L838)
* FrameworkServlet.processRequest()
    * [https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#952](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L952)
* DispatcherSevlet.doService()
    * [https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L866](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L866)

## HandlerExecutionChain 결정 및 실행

* DispatcherServlet.doDispatch()
    * [https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#924](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L924)

* Handler Execution Chain (Controller + Interceptors) 실행

```
if (!mappedHandler.applyPreHandle(processedRequest, response)) {
  return;
}

// Actually invoke the handler.
mv = ha.handle(processedRequest, response, mappedHandler.getHandler());

// ...

mappedHandler.applyPostHandle(processedRequest, response, mv);
```

## 뷰 렌더링 / 예외 처리

* DispatcherServlet.processDispatchResult()
    * [https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L1022](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L1022)
* DispatcherServlet.processHandlerException()
    * [https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L1216](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L1216)
* DispatcherServlet.render()
    * [https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L1255](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L1255)

## 요청 처리 종료

* DispatcherServlet.processRequest().finally
    * [https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L985](https://github.com/spring-projects/spring-framework/blob/4.3.x/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L985)
