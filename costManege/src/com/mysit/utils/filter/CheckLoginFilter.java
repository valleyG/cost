package com.mysit.utils.filter;

import com.mysit.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登陆过滤器
 * 如果没有登陆不允许发出任何请求，并跳转到登陆页面
 */
@WebFilter("/*")
public class CheckLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //强转型
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        //获取session对象
        HttpSession session=req.getSession();
        //获取session对象里面的userInfo对象
        User user= (User) session.getAttribute("userInfo");
        if(user==null){//如果user对象为空
            if(req.getRequestURI().contains("/view/login.jsp")){
                filterChain.doFilter(req, servletResponse);
            } else if(req.getRequestURI().contains("/resource")){//如果请求的是资源类则放行
                filterChain.doFilter(req, servletResponse);
            }else if(req.getRequestURI().contains("/view/login_info.jsp")){//如果请求的是login_info.jsp则放行
                filterChain.doFilter(req, servletResponse);
            }else if(req.getRequestURI().contains("/view")){//如果请求的是页面的话就转到外部的登陆页面
                req.getRequestDispatcher("/view/login_info.jsp").forward(req, servletResponse);
            }else if(req.getRequestURI().contains("/doLogin")){
                filterChain.doFilter(req, servletResponse);
            }
            else {//其他的请求
                req.getRequestDispatcher("/view/login_info.jsp").forward(req, servletResponse);
            }
        }else{
            req.setAttribute("userAccount", user.getUserAccount());
            req.setAttribute("userPwd", user.getUserPwd());
            filterChain.doFilter(req, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
