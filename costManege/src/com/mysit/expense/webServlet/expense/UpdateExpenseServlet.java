package com.mysit.expense.webServlet.expense;

import com.my.web.servlet.RequestBeanUtils;
import com.mysit.expense.service.IExpenseService;
import com.mysit.expense.service.impl.IExpenseServiceImpl;
import com.mysit.pojo.AuditRecord;
import com.mysit.pojo.Cost;
import com.mysit.pojo.Expense;
import com.mysit.pojo.ExpenseDetail;
import com.mysit.system.service.ICostService;
import com.mysit.system.service.IUserService;
import com.mysit.system.service.impl.ICostServiceImpl;
import com.mysit.system.service.impl.IUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/expense/updateExpense")
public class UpdateExpenseServlet extends HttpServlet{
    IExpenseService expenseService = new IExpenseServiceImpl();
    ICostService costService = new ICostServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int expenseId = Integer.parseInt(req.getParameter("expenseId"));//获取请求的报销单号

        //获取报销单信息
        Expense expense = expenseService.queryOneExpense(expenseId);
        //获取报销单明细
        List<ExpenseDetail> details = expenseService.queryExpenseDetails(expenseId);

        //获取审核记录
        List<AuditRecord> auditRecords = expenseService.queryAuditRecordList(expenseId);

        //获取费用列表
        List<Cost> costList = costService.queryCostList(new Cost());

        ///将报销单信息,报销单明细，审核记录全部传回页面
        req.setAttribute("expense", expense);
        req.setAttribute("details", details);
        req.setAttribute("auditRecords", auditRecords);
        req.setAttribute("costList", costList);

        req.getRequestDispatcher("/view/expense/expense/expense_update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受页面传上来的数据
        Expense expense = RequestBeanUtils.requestToBean(req, Expense.class);
        boolean flag = expenseService.updateExpense(expense);
        List<ExpenseDetail> details=null;
        List<AuditRecord> records=null;
        List<Cost> costs=null;
        if (flag) {
            details=expenseService.queryExpenseDetails(expense.getExpenseId());
            records=expenseService.queryAuditRecordList(expense.getExpenseId());
            costs=costService.queryCostList(new Cost());
            req.setAttribute("tip", "保存成功");
            req.setAttribute("details", details);
            req.setAttribute("auditRecords", records);
            req.setAttribute("costList", costs);
        }else {
            req.setAttribute("tip", "保存失败");
        }
        req.setAttribute("expense", expense);
        req.getRequestDispatcher("/view/expense/expense/expense_update.jsp").forward(req, resp);
    }
}
