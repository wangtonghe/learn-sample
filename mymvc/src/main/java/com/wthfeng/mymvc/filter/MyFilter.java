package com.wthfeng.mymvc.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author wangtonghe
 * @date 2017/4/29 17:34
 */
public class MyFilter implements Filter {

    private String param;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(filterConfig.getInitParameter("myParam"));
        System.out.println("filter初始化");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("开始filter");
        chain.doFilter(request,response);
        System.out.println("结束filter");

    }

    @Override
    public void destroy() {
        System.out.println("filter销毁");

    }
}
