package com.dj.delegatepattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    private void doDispatcher(HttpServletRequest req, HttpServletResponse rsp) throws Exception{
        String uri = req.getRequestURI();
        String mid = req.getParameter("mid");

        if("getMemberById".equals(uri)){
            new MemberController().getMemberById();
        }else if("logout".equals(uri)){
            new SystemController().logout();
        }else{
            rsp.getWriter().write("404 NOT FOUND");
        }

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatcher(req,resp);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
