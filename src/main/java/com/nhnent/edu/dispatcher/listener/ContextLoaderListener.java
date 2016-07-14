package com.nhnent.edu.dispatcher.listener;

import com.nhnent.edu.dispatcher.repository.MemberRepositoryImpl;
import com.nhnent.edu.dispatcher.repository.MemberRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        MemberRepository memberRepository = new MemberRepositoryImpl();

        sc.setAttribute("memberRepository", memberRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
