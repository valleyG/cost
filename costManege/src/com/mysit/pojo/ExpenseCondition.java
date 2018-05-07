package com.mysit.pojo;

/**
 * 报销单查询条件实体
 */
public class ExpenseCondition {
    private  int userId;
    private String userName;
    private String startDate;
    private String endDate;
    private String expenseName;
    private String expenseState;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getExpenseState() {
        return expenseState;
    }

    public void setExpenseState(String expenseState) {
        this.expenseState = expenseState;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    @Override
    public String toString() {
        return "ExpenseCondition{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", expenseName='" + expenseName + '\'' +
                ", expenseState='" + expenseState + '\'' +
                '}';
    }
}
