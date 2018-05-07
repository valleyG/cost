package com.mysit.finance.webServlet.financeAudit;

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

@WebServlet("/finance/expenseList")
public class FinanceauditListServley extends HttpServlet {
    IExpenseService expenseService = new IExpenseServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取页面请求的数据
        ExpenseCondition expenseCondition = RequestBeanUtils.requestToBean(req, ExpenseCondition.class);
        expenseCondition.setExpenseState("2");
        System.out.println(expenseCondition);
        List<Expense> expenses =  expenseService.queryExpenseList(expenseCondition);
        System.out.println(expenses);
        req.setAttribute("expenses", expenses);
        req.getRequestDispatcher("/view/finance/financAaudit/financeaudit_list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExpenseCondition expenseCondition = RequestBeanUtils.requestToBean(req, ExpenseCondition.class);
        expenseCondition.setExpenseState("2");//z只能查询经理审核通过的报销单
        List<Expense> expenses = expenseService.queryExpenseList(expenseCondition);
        req.setAttribute("expenses", expenses);
        req.setAttribute("expenseCondition", expenseCondition);
        req.getRequestDispatcher("/view/finance/financAaudit/financeaudit_list.jsp").forward(req, resp);
    }
}
