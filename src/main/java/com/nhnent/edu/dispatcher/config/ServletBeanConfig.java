package com.nhnent.edu.dispatcher.config;

import com.nhnent.edu.dispatcher.servlet.LogoutServlet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// TODO #2-3 : Java Config
@Configuration
public class ServletBeanConfig {
    // TODO #2-4 : BeanNameUrlHandlerMapping을 이용하도록 LogoutServlet 빈을 생성하세요(*)
    // cf.)
    //  <bean name="/logout" class="com.nhnent.edu.dispatcher.servlet.LogoutServlet" />
    @Bean(name = "/logout")
    public LogoutServlet logoutServlet() {
        return new LogoutServlet();
    }

}
