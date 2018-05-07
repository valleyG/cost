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
import java.util.List;

@WebServlet("/system/userQuery")
public class UserQueryServlet extends HttpServlet{
    IUserService userService=new IUserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //1.接受查询条件
        User user= RequestBeanUtils.requestToBean(req, User.class);

        //2.调用service
        List<User> userList=userService.queryUsers(user);
        //3.返回参数跳转页面
        //回现查询条件
        req.setAttribute("queryCon", user);
        req.setAttribute("userList", userList);
        req.getRequestDispatcher("/view/system/user/userinfo_list.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doPost(req, resp);
    }
}
