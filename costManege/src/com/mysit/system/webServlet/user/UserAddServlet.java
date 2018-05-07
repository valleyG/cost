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

@WebServlet("/system/addUser")
public class UserAddServlet extends HttpServlet {
    IUserService userService = new IUserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       1.获取页面请求的信息
//       通过工具类直接获得表单提交的数据，注意：对象中的属性名称必须对象表单中的name属性不然就获取不到。
        User user = RequestBeanUtils.requestToBean(req, User.class);
//      2.调用业务逻辑
        boolean flag = userService.addUser(user);
//      3.返回页面和参数
        if (flag) {
            req.setAttribute("tip", "添加成功");
        } else {
            req.setAttribute("tip", "添加失败");
        }
        req.getRequestDispatcher("/view/system/user/userinfo_add.jsp").forward(req, resp);
    }
}
