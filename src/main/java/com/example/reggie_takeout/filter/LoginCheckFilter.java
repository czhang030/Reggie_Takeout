package com.example.reggie_takeout.filter;


import com.alibaba.fastjson.JSON;
import com.example.reggie_takeout.common.BaseContext;
import com.example.reggie_takeout.pojo.Result;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;

@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //路径匹配
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

//        将拦截到的URI输出到日志，{}是占位符，将自动填充request.getRequestURI()的内容 (log函数的特点)
//        log.info("拦截到的URL: {}",request.getRequestURI());

        //1.获取本次请求的URI
        String requestURI = request.getRequestURI();
        log.info("拦截到的请求路径: {}",requestURI);

        //定义不需要的请求
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        //2.判断本次请求是否需要处理
        boolean check = check(urls, requestURI);


        //3.如果不需要处理，则直接放行
        if (check){
            log.info("本次请求: {},不需要处理",requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        //4.利用session判断登录状态，如果已登录则直接放行
        if (request.getSession().getAttribute("employee")!=null){
            log.info("用户已经登录，其id为: {}",request.getSession().getAttribute("employee"));
            Long emplId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(emplId);
            filterChain.doFilter(request, response);
            return;
        }

        //5.如果未登录则返回未登录结果,通过输出流方式向客户端页面响应数据
        //log是打给后端运行控制台看的， response.getWriter().write(JSON.toJSONString(Object)是写给客户端响应数据的;
        log.info("用户未登录");
        log.info("用户id: {}",request.getSession().getAttribute("employee"));
        response.getWriter().write(JSON.toJSONString(Result.error("NOTLOGIN")));


    }

    public boolean check(String[] urls, String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                //匹配
                return true;
            }
        }
        //不匹配
        return false;
    }
}
