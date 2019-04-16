package com.wthfeng.learn.spring.mvc.controller;

import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wangtonghe
 * @since 2019/4/4 20:08
 */
public class HandlerController implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter printWriter = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        printWriter.write("<html><head><head><body><h1>");
        printWriter.write("Hello message!");
        printWriter.write("</h1></body></html>");
        printWriter.close();

    }
}
