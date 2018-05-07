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

@WebServlet("/system/userUpdate")
public class UserUpdateServlet extends HttpServlet {
    IUserService userService = new IUserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //当以post方法请求该servlet时就是真正的修改数据
        //1.获取请求的用户信息
        User user=RequestBeanUtils.requestToBean(req, User.class);
        boolean flag=userService.update(user);
        if (flag){//如果修改成功，则添加一个tip属性，返回修改页面
            req.setAttribute("tip", "用户信息修改成功！");
            //修改过后的对象回显回去
            req.setAttribute("user",user);

        }else {//否者修改失败，将为修改成功的user传回去，并提示修改失败。
            req.setAttribute("tip", "用户信息修改失败！");
        }
        req.getRequestDispatcher("/view/system/user/userinfo_update.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求过来的user对象
        User reqUser = RequestBeanUtils.requestToBean(req, User.class);
        //通过请求的userId去查询请求的对象user
        User user = userService.queryUserOne(reqUser.getUserId());

        req.setAttribute("user", user);
        //跳转页面,并将查询到的用户信息传给页面
        req.getRequestDispatcher("/view/system/user/userinfo_update.jsp").forward(req, resp);

    }
}
