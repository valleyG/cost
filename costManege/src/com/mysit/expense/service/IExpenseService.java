package com.mysit.expense.service;

import com.mysit.pojo.AuditRecord;
import com.mysit.pojo.Expense;
import com.mysit.pojo.ExpenseCondition;
import com.mysit.pojo.ExpenseDetail;

import java.util.List;

public interface IExpenseService {
    boolean addExpense (Expense expense);
    List<Expense> queryExpenseList(Expense expense);
    Expense queryOneExpense(int expenseId);
    List<ExpenseDetail> queryExpenseDetails(int expenseId);
    List<AuditRecord> queryAuditRecordList(int expenseId);

    boolean updateAuditRecord(AuditRecord auditRecord);

    boolean updateExpense(AuditRecord auditRecord);

    List<Expense> queryExpenseList(ExpenseCondition expenseCondition);

    boolean updateExpense(Expense expense);
}
