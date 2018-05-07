package com.mysit.system.webServlet.user;

import com.mysit.system.service.IUserService;
import com.mysit.system.service.impl.IUserServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 检查用户名是否已经存在
 */
@WebServlet("/system/checkUser")
public class UserAccountCheck extends HttpServlet {
    IUserService userService=new IUserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.获取用户名
        String account = req.getParameter("userAccount");
//        2.调用业务逻辑层
        boolean flag=userService.checkAccount(account);
//        3.返回结果
        JSONObject jsonObject=new JSONObject();
        if(flag){
            jsonObject.put("state", true);
            jsonObject.put("tip", "");
        }else{
            jsonObject.put("state", false);
            jsonObject.put("tip", "该用户已存在");
        }
        PrintWriter out =resp.getWriter();
        out.println(jsonObject);
        out.close();
    }
}

