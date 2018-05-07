package com.mysit.expense.webServlet.expense;

import com.mysit.expense.service.IExpenseService;
import com.mysit.expense.service.impl.IExpenseServiceImpl;
import com.mysit.pojo.AuditRecord;
import com.mysit.pojo.Expense;
import com.mysit.pojo.ExpenseDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/expense/showExpenseDetails")
public class ShowExpenseDetail extends HttpServlet{
    IExpenseService expenseService=new IExpenseServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int expenseId = Integer.parseInt(req.getParameter("expenseId"));
        Expense expense = expenseService.queryOneExpense(expenseId);//获取报销单信息
        List<ExpenseDetail> details = expenseService.queryExpenseDetails(expenseId);//获取报销单明细
        List<AuditRecord> records = expenseService.queryAuditRecordList(expenseId);//获取报销单审核记录

        //将信息返回页面
        req.setAttribute("expense", expense);
        req.setAttribute("details", details);
        req.setAttribute("records", records);

        req.getRequestDispatcher("/view/expense/expense/expense_show.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
