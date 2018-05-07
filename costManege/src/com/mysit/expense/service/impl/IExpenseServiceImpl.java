package com.mysit.expense.service.impl;

import com.mysit.expense.dao.IExpenseDao;
import com.mysit.expense.dao.imple.IExpenseDaoImpl;
import com.mysit.expense.service.IExpenseService;
import com.mysit.pojo.AuditRecord;
import com.mysit.pojo.Expense;
import com.mysit.pojo.ExpenseCondition;
import com.mysit.pojo.ExpenseDetail;

import java.util.List;

public class IExpenseServiceImpl implements IExpenseService {
    IExpenseDao expenseDao=new IExpenseDaoImpl();
    @Override
    public boolean addExpense(Expense expense) {
        int rows=expenseDao.addExpense(expense);
        if(rows>0)
            return true;
        return false;
    }

    @Override
    public List<Expense> queryExpenseList(Expense expense) {
        return expenseDao.queryExpenseList(expense);
    }

    @Override
    public Expense queryOneExpense(int expenseId) {
        return expenseDao.queryOneExpense(expenseId);
    }

    @Override
    public List<ExpenseDetail> queryExpenseDetails(int expenseId) {
        return expenseDao.queryExpenseDetails(expenseId);
    }

    @Override
    public List<AuditRecord> queryAuditRecordList(int expenseId) {
        return expenseDao.queryAuditRecordList(expenseId);
    }

    @Override
    public boolean updateAuditRecord(AuditRecord auditRecord) {
        int rows=expenseDao.updateAuditRecord(auditRecord);
        if (rows>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateExpense(AuditRecord auditRecord) {
        int rows = expenseDao.updateExpense(auditRecord);
        if (rows>0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Expense> queryExpenseList(ExpenseCondition expenseCondition) {
        return expenseDao.queryExpenseList(expenseCondition);
    }

    /**
     * 个人修改报销订单
     * @param expense
     * @return
     */
    @Override
    public boolean updateExpense(Expense expense) {
        int rows = expenseDao.updateExpense(expense);
        if (rows>0) {
            return true;
        }else {
            return false;
        }
    }
}
