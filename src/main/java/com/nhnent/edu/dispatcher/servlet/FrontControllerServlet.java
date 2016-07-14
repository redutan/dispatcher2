package com.nhnent.edu.dispatcher.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO: Front Controller 도입
public class FrontControllerServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* content type */
        resp.setContentType("text/html; charset=UTF-8");

        String servletPath = req.getServletPath();

        /* 분기 처리 */
        String pageControllerPath = null;
        // 로그인
        if ("/login.do".equals(servletPath)) {
            pageControllerPath = "/login";
        }
        // 로그아웃
        else if ("/logout.do".equals(servletPath)) {
            pageControllerPath = "/logout";
        }
        // 회원 목록
        if ("/member/list.do".equals(servletPath)) {
            pageControllerPath = "/member/list";
        }

        /* 페이지 컨트롤러로 위임 */
        RequestDispatcher rd = req.getRequestDispatcher(pageControllerPath);
        rd.include(req, resp);

        /* 뷰 페이지로 위임 */
        String viewUrl = (String) req.getAttribute("viewUrl");
        if (viewUrl.startsWith("redirect:")) {
            resp.sendRedirect(viewUrl.substring(9));
        }
        else {
            rd = req.getRequestDispatcher(viewUrl);
            rd.include(req, resp);
        }
    }

}
