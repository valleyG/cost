package com.mysit.finance.webServlet.financeAudit;

import com.my.web.servlet.RequestBeanUtils;
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

@WebServlet("/finance/financeAudit")
public class FinanceAuditServlet extends HttpServlet{
    IExpenseService expenseService = new IExpenseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取页面传上来的expenseId
        int expenseId = Integer.parseInt(req.getParameter("expenseId"));
        //获取报销单信息
        Expense expense = expenseService.queryOneExpense(expenseId);
        //获取报销单的明细
        List<ExpenseDetail> details = expenseService.queryExpenseDetails(expenseId);
        //获取报销单的审核明细
        List<AuditRecord> auditRecords = expenseService.queryAuditRecordList(expenseId);
        //将三个信息传回页面
        req.setAttribute("expense", expense);
        req.setAttribute("details", details);
        req.setAttribute("auditRecords", auditRecords);
        req.getRequestDispatcher("/view/finance/financAaudit/financeaudit_audit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受页面传上来的数据
       AuditRecord auditRecord = RequestBeanUtils.requestToBean(req, AuditRecord.class);
       expenseService.updateAuditRecord(auditRecord);
       //修改报销单的状态
        boolean flag1 = expenseService.updateExpense(auditRecord);
        //修改审核记录
        boolean flag2 = expenseService.updateAuditRecord(auditRecord);
        if(flag1&&flag2){
            req.setAttribute("info", "审核成功");
            req.getRequestDispatcher("/view/finance/financAaudit/financeaudit_list.jsp").forward(req, resp);//跳转回审核报销单列表页面
        }else{
            req.setAttribute("tip", "审核失败");
            req.getRequestDispatcher("/view/finance/financAaudit/financeaudit_audit.jsp").forward(req, resp);
        }
    }
}
