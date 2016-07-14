package com.nhnent.edu.dispatcher.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();

        // TODO: Front Controller에서 공통 처리하므로 제거하고 viewUrl 설정
        req.setAttribute("viewUrl", "redirect:/login.do");
//        resp.sendRedirect("/login");
    }

}
