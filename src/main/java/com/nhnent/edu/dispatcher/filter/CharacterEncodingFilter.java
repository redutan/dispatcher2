package com.nhnent.edu.dispatcher.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    FilterConfig config;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(config.getInitParameter("encoding"));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
