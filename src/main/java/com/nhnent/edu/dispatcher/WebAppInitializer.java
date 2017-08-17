package com.nhnent.edu.dispatcher;

import com.nhnent.edu.dispatcher.configuration.RootAppContextConfig;
import com.nhnent.edu.dispatcher.configuration.WebAppContextConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

// TODO: 2. WebApplicationInitializer - Servlet 3.0+ ServletContainerInitializer
public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // create root application context.
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootAppContextConfig.class);

        // manage the lifecycle of the root application context.
        servletContext.addListener(new ContextLoaderListener(rootContext));

        // register character encoding filter.
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);

        servletContext.addFilter("encodingFilter", encodingFilter);

        // create servlet application context.
        AnnotationConfigWebApplicationContext webAppContext = new AnnotationConfigWebApplicationContext();
        webAppContext.register(WebAppContextConfig.class);

        // register dispatcher servlet.
        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcherServlet", new DispatcherServlet(webAppContext));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");
    }

}
