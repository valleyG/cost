package com.mysit.system.webServlet.user;

import com.my.web.servlet.RequestBeanUtils;
import com.mysit.finance.service.ISalaryService;
import com.mysit.finance.service.impl.ISalaryServiceImp;
import com.mysit.pojo.Salary;
import com.mysit.pojo.User;
import com.mysit.system.service.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/system/queryMySalary")
public class QueryMySalary extends HttpServlet {
    ISalaryService salaryService = new ISalaryServiceImp();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Salary salary = RequestBeanUtils.requestToBean(req, Salary.class,"yyyy-MM");
        HttpSession session = req.getSession();
        User myInfo = (User) session.getAttribute("userInfo");
        int userId = myInfo.getUserId();
        salary.setUserId(userId);
        List<Salary> salaries = salaryService.querySalaryList(salary);
        //将工资列表，和查询条件返回给页面
        System.out.println(salaries);
        req.setAttribute("salaries", salaries);
        req.setAttribute("salary", salary);
        req.getRequestDispatcher("/view/system/user/salarypayment_mylist.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
