package com.wthfeng.learn.spring.mvc.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author wangtonghe
 * @since 2019/4/4 10:53
 */
public class MyDispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryString = req.getQueryString();
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html;charset=UTF-8");
        printWriter.write("<html><head><head><body><h1>");
        printWriter.write("Hello " + queryString);
        printWriter.write("</h1></body></html>");
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> map = req.getParameterMap();

        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html;charset=UTF-8");
        printWriter.write("<html><head><head><body><h1>");
        printWriter.write("Hello post");
        printWriter.write("</h1></body></html>");
        printWriter.close();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        String name = config.getServletName();
        System.out.println("servlet name:" + name);
        String location = config.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation:" + location);
    }
}
