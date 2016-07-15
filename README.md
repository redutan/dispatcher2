# DispatcherServlet의 요청 처리 프로세스

## DispatcherServlet 소스 들여다보기

[https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java)

## DispatcherServlet의 요청 처리

## bootstrap

* DispatcherServlet static initialization block : 기본 전략 설정 파일 로딩
    * [https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L273](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L273)
* DispatcherServlet.properties : 기본 전략 설정 파일
    * [https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/resources/org/springframework/web/servlet/DispatcherServlet.properties](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/resources/org/springframework/web/servlet/DispatcherServlet.properties)
* HttpServletBean.init()
    * [https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/HttpServletBean.java#L116](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/HttpServletBean.java#L116)
* FrameworkServlet.initServletBean()
    * [https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L485](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L485)
* FrameworkServlet.initWebApplicationContext()
    * [https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L521](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L521)
* @MVC
    * AnnotationDrivenBeanDefinitionParser: `<mvc: annotation-driven />`
        * [https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/config/AnnotationDrivenBeanDefinitionParser.java](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/config/AnnotationDrivenBeanDefinitionParser.java)
        * MvcNamespaceUtils.registerDefaultComponents()
            * `<mvc:resources />` 사용 시 `@Controller` 설정이 먹지 않는 오류를 해결하기 위해
            BeanNameUrlHandlerMapping, HttpRequestHandlerAdapter, SimpleControllerHandlerAdapter 를 추가 등록
            * [https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/config/MvcNamespaceUtils.java#L60](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/config/MvcNamespaceUtils.java#L60)
    * WebMvcConfigurationSupport: `@EnableWebMvc`
        * [https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/config/annotation/WebMvcConfigurationSupport.java](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/config/annotation/WebMvcConfigurationSupport.java)
* FrameworkServlet.configureAndRefreshWebApplicationContext()
    * [https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L634](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L634)
    * cf.) ApplicationContext 와 WebApplicationContext의 차이는?
* DispatcherServlet.onRefresh() -> DispatcherServlet.initStrategies()
    * [https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L483](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L483)
* initialize each strategies ...

## 요청 선처리 작업

* FrameworkServlet.service() -> HttpServlet.doGet()/doPost()/... -> FrameworkServlet.processRequest()
    * [https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L833](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L833)
* FrameworkServlet.processRequest()
    * [https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L946](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L946)
* DispatcherSevlet.doService()
    * [https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L862](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L862)

## HandlerExecutionChain 결정 및 실행

* DispatcherServlet.doDispatch()
    * [https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L920](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L920)
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
    * [https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L1018](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L1018)
* DispatcherServlet.processHandlerException()
    * [https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L1187](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L1187)
* DispatcherServlet.render()
    * [https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L1226](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java#L1226)

## 요청 처리 종료

* DispatcherServlet.processRequest().finally
    * [https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L979](https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/FrameworkServlet.java#L979)
