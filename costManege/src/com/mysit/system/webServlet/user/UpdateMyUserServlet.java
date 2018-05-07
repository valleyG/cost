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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/system/updateMyUser")
public class UpdateMyUserServlet extends HttpServlet{
    IUserService userService=new IUserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取页面传来的信息
        User user = RequestBeanUtils.requestToBean(req, User.class);
        System.out.println(user);
        //调用业务逻辑层
        boolean flag = userService.update(user);
        HttpSession session=req.getSession();//获取session对象
        if (flag) {
            req.setAttribute("tip", "修改成功");
            session.setAttribute("userInfo", user);
        }else{
            req.setAttribute("tip", "修改失败");
        }
        //将数据传回，并跳转页面
        req.getRequestDispatcher("/view/system/user/userinfo_show.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
