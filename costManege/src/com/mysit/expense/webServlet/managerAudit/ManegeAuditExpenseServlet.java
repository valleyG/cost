package com.mysit.expense.webServlet.managerAudit;

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

@WebServlet("/expense/manageAuditExpense")
public class ManegeAuditExpenseServlet extends HttpServlet{
    IExpenseService expenseService=new IExpenseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int expenseId=Integer.parseInt(req.getParameter("expenseId"));//获取得到的报销单号
        Expense expense = expenseService.queryOneExpense(expenseId);//获取报销单信息

        List<ExpenseDetail> details = expenseService.queryExpenseDetails(expenseId);//获取报销单的明细

        //获取审核记录
        List<AuditRecord> records = expenseService.queryAuditRecordList(expenseId);
        if(expense != null){
            //将报销单信息添加到返回
            req.setAttribute("expense", expense);
            //将报销明细添加到返回
            req.setAttribute("details", details);
        }
        if(records!=null){
            req.setAttribute("records", records);
        }
        req.getRequestDispatcher("/view/expense/managerAudit/expense_audit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取post上来的expense对象信息
        AuditRecord auditRecord= RequestBeanUtils.requestToBean(req, AuditRecord.class);
        System.out.println(auditRecord);
        //提交业务逻辑层
    boolean auditFlag = expenseService.updateAuditRecord(auditRecord);//修改审核记录
    boolean expenseFlag=expenseService.updateExpense(auditRecord);//修改报销单的审核状态
        if(auditFlag&&expenseFlag){
        req.setAttribute("tip", "成功审核");
    }
        req.getRequestDispatcher("/view/expense/managerAudit/expense_audit.jsp").forward(req, resp);
}
}
