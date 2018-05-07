package com.mysit.pojo;

/**
 * 报销单明细实体对象
 */
public class ExpenseDetail {
    private int detailId;
    private int expenseId;
    private int costId;
    private String expenseDesc;
    private float expenseMoney;
    private String costName;

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getCostId() {
        return costId;
    }

    public void setCostId(int costId) {
        this.costId = costId;
    }

    public String getExpenseDesc() {
        return expenseDesc;
    }

    public void setExpenseDesc(String expenseDesc) {
        this.expenseDesc = expenseDesc;
    }

    public float getExpenseMoney() {
        return expenseMoney;
    }

    public void setExpenseMoney(float expenseMoney) {
        this.expenseMoney = expenseMoney;
    }
}
