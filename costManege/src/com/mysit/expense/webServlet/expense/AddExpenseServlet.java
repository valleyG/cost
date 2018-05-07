package com.mysit.expense.webServlet.expense;

import com.my.web.servlet.RequestBeanUtils;
import com.mysit.expense.service.IExpenseService;
import com.mysit.expense.service.impl.IExpenseServiceImpl;
import com.mysit.pojo.Cost;
import com.mysit.pojo.Expense;
import com.mysit.system.service.ICostService;
import com.mysit.system.service.impl.ICostServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/expense/addExpense")
public class AddExpenseServlet extends HttpServlet {
    ICostService costService = new ICostServiceImpl();
    IExpenseService expenseService = new IExpenseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //跳转页面
        List<Cost> costs = costService.queryCostList(new Cost());
        req.setAttribute("costs", costs);
        req.getRequestDispatcher("/view/expense/expense/expense_add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受页面传来的数据
        Expense expense = RequestBeanUtils.requestToBean(req, Expense.class);
        //提交到业务逻辑层
        boolean flag = expenseService.addExpense(expense);
        if(expense.getExpenseState()=="1"){
            if(flag){//提交并保存
                req.setAttribute("tip", "提交并保存成功");
            }else {
                req.setAttribute("tip", "提交并保存失败");
            }
        }else{
            if(flag){//提交并保存
                req.setAttribute("tip", "保存成功");
            }else {
                req.setAttribute("tip", "保存失败");
            }
        }
        //返回页面
        req.getRequestDispatcher("/view/expense/expense/expense_add.jsp").forward(req, resp);

    }
}
