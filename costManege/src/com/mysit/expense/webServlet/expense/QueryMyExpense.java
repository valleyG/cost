package com.mysit.expense.webServlet.expense;

import com.my.web.servlet.RequestBeanUtils;
import com.mysit.expense.service.IExpenseService;
import com.mysit.expense.service.impl.IExpenseServiceImpl;
import com.mysit.pojo.Expense;
import com.mysit.pojo.ExpenseCondition;
import com.mysit.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/expense/queryMyExpense")
public class QueryMyExpense extends HttpServlet{
    IExpenseService expenseService=new IExpenseServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取查询条件
        ExpenseCondition expenseCondition = RequestBeanUtils.requestToBean(req, ExpenseCondition.class);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("userInfo");//获取当前用户的id值
        expenseCondition.setUserId(user.getUserId());

        //提交到服务层
        List<Expense> expenses = expenseService.queryExpenseList(expenseCondition);

        //转回页面
        req.setAttribute("expenses", expenses);
        req.setAttribute("expense", expenseCondition);

        req.getRequestDispatcher("/view/expense/expense/expense_mylist.jsp").forward(req, resp);
    }
}
