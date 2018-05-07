package com.mysit.expense.webServlet.expense;

import com.my.web.servlet.RequestBeanUtils;
import com.mysit.expense.service.IExpenseService;
import com.mysit.expense.service.impl.IExpenseServiceImpl;
import com.mysit.pojo.Expense;
import com.mysit.pojo.ExpenseCondition;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/expense/queryExpense")
public class QueryExpenseServlet extends HttpServlet {
    IExpenseService expenseService = new IExpenseServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cmd = req.getParameter("cmd");
        //如果请求的是经理审核的话
        if (cmd.equals("queryManageExpense")) {
            Expense expense = new Expense();
            expense.setExpenseState("1");///设置查询提交未经理审核的报销单
            List<Expense> expenses = expenseService.queryExpenseList(expense);
            req.setAttribute("expenses", expenses);
        }


        //返回页面
        req.getRequestDispatcher("/view/expense/managerAudit/expense_managerlist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExpenseCondition expenseCondition = RequestBeanUtils.requestToBean(req, ExpenseCondition.class);//获取查询条件
        System.out.println(expenseCondition);
        List<Expense> expenses = expenseService.queryExpenseList(expenseCondition);

        req.setAttribute("expense",expenseCondition );
        req.setAttribute("expenses", expenses);

        req.getRequestDispatcher("/view/expense/managerAudit/expense_managerlist.jsp").forward(req, resp);

    }
}
