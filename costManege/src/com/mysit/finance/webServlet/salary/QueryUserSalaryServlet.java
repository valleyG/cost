package com.mysit.finance.webServlet.salary;

import com.mysit.pojo.User;
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

@WebServlet("/finance/querySalary")
public class QueryUserSalaryServlet extends HttpServlet {
    IUserService userService = new IUserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       //json传输选择用户的工资底薪
        int userId = Integer.parseInt(req.getParameter("userId"));
        User user = userService.queryUserOne(userId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userSalary", user.getUserBasic());
        PrintWriter out = resp.getWriter();
        out.println(jsonObject);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
