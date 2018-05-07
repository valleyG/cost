package com.mysit.system.webServlet.user;

import com.my.web.servlet.RequestBeanUtils;
import com.mysit.pojo.User;
import com.mysit.system.service.IUserService;
import com.mysit.system.service.impl.IUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

@WebServlet("/system/deleteUsers")
public class DeletUsersServlet extends HttpServlet {
    IUserService userService = new IUserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取页面传上来的参数
        User user = RequestBeanUtils.requestToBean(req, User.class);
        //2.调用逻辑层
        boolean flag=userService.delete(user);
        if(flag){//如果修改成功
            req.getRequestDispatcher("/system/userQuery").forward(req, resp);
        }else{//修改失败
        }
    }
}
