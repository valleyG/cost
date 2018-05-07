package com.mysit.finance.webServlet.salary;

import com.my.web.servlet.RequestBeanUtils;
import com.mysit.finance.service.ISalaryService;
import com.mysit.finance.service.impl.ISalaryServiceImp;
import com.mysit.pojo.Salary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/finance/querySalaryList")
public class QuerySalaryListServlet extends HttpServlet {
    ISalaryService salaryService = new ISalaryServiceImp();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Salary salary = RequestBeanUtils.requestToBean(req, Salary.class,"yyyy-MM");
        //查询工资列表
        List<Salary> salaries = salaryService.querySalaryList(salary);
        //将工资列表，和查询条件返回给页面
        req.setAttribute("salaries", salaries);
        req.setAttribute("salary", salary);
        req.getRequestDispatcher("/view/finance/salary/salarypayment_list.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取所有的薪资
        List<Salary> salaries = salaryService.querySalaryList(new Salary());
        //返回页面
        req.setAttribute("salaries", salaries);
        //跳转页面
        req.getRequestDispatcher("/view/finance/salary/salarypayment_list.jsp").forward(req, resp);
    }
}
