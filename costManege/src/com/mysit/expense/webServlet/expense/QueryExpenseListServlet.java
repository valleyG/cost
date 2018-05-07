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

@WebServlet("/expense/queryExpenseList")
public class QueryExpenseListServlet extends HttpServlet {
    IExpenseService expenseService = new IExpenseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExpenseCondition expenseCondition = RequestBeanUtils.requestToBean(req, ExpenseCondition.class);
        List<Expense> expenses = expenseService.queryExpenseList(expenseCondition);
        req.setAttribute("expenses", expenses);
        req.setAttribute("expense", expenseCondition);
        req.getRequestDispatcher("/view/expense/expense/expense_list.jsp").forward(req, resp);
    }
}
