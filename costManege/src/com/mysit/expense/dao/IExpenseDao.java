package com.mysit.expense.dao;

import com.mysit.pojo.AuditRecord;
import com.mysit.pojo.Expense;
import com.mysit.pojo.ExpenseCondition;
import com.mysit.pojo.ExpenseDetail;

import java.util.List;

public interface IExpenseDao {
    int addExpense(Expense expense);
    List<Expense> queryExpenseList(Expense expense);

    Expense queryOneExpense(int expenseId);

    List<ExpenseDetail> queryExpenseDetails(int expenseId);

    List<AuditRecord> queryAuditRecordList(int expenseId);

    int updateAuditRecord(AuditRecord auditRecord);

    int updateExpense(AuditRecord auditRecord);

    /**
     * 通过报销单查询条件查询报销单
     * @param expenseCondition
     * @return
     */
    List<Expense> queryExpenseList(ExpenseCondition expenseCondition);

    int updateExpense(Expense expense);
}
