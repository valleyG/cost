package com.mysit.finance.webServlet.salary;

import com.my.web.servlet.RequestBeanUtils;
import com.mysit.finance.service.ISalaryService;
import com.mysit.finance.service.impl.ISalaryServiceImp;
import com.mysit.pojo.Salary;
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

@WebServlet("/finance/addSalary")
public class AddSalaryServlet extends HttpServlet {
    IUserService userService = new IUserServiceImpl();
    ISalaryService salaryService = new ISalaryServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取上传上来的添加的工资对象
        Salary salary = RequestBeanUtils.requestToBean(req, Salary.class,"yyyy-MM");
        boolean flag = salaryService.addSalary(salary);
        if (flag) {
            req.setAttribute("tip", "薪资发放成功");
        }else{
            req.setAttribute("tip", "薪资发放失败");
        }
        List<User> users = userService.queryUsers(new User());
        req.setAttribute("users", users);
        req.getRequestDispatcher("/view/finance/salary/salarypayment_add.jsp").forward(req, resp);
    }
}
