package com.wthfeng.mymvc.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wangtonghe
 * @date 2017/4/29 15:30
 */
//@WebServlet(name = "hello",value = "/hello")
public class HelloServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("开始执行servlet");
        String name  = req.getParameter("name");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("<html><head><head><body><h4>");
        printWriter.write("Hello "+name);
        printWriter.write("</h4></body></html>");
        printWriter.close();
        System.out.println("结束servlet");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("初始化servlet  hello");
    }

    @Override
    public void destroy() {
        System.out.println("servlet销毁 hello");
    }
}
