package com.nhnent.edu.dispatcher.configuration;

import com.nhnent.edu.dispatcher.controller.LogoutController;
import com.nhnent.edu.dispatcher.controller.MemberListController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Locale;

// TODO: 4. servlet application context

@Configuration

// <mvc:annotation-driven />
@EnableWebMvc

// <context:component-scan base-package="com.nhnent.edu.dispatcher" />
@ComponentScan("com.nhnent.edu.dispatcher")
public class WebAppContextConfig extends WebMvcConfigurerAdapter {
    @Bean(name = "/member/list")
    public MemberListController memberListController() {
        return new MemberListController();
    }

    @Bean(name = "/logout")
    public LogoutController logoutController() {
        return new LogoutController();
    }

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages/msg");

        return messageSource;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");

        return interceptor;
    }

    /*
    <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/**" />
            <ref bean="localeChangeInterceptor" />
        </mvc:interceptor>
    </mvc:interceptors>
    */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public LocaleResolver localeResolver() {
        FixedLocaleResolver localeResolver = new FixedLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("ko"));

        return localeResolver;
    }

}
