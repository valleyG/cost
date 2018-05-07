package com.mysit.pojo;

import java.util.Arrays;
import java.util.Date;

/**
 * 报销单实体
 */
public class Expense {
    //报销单属性
    private int expenseId;
    private int userId;
    private String userName;
    private String stateName;//报销单状态名字
    private String expenseName;
    private float expenseTotal;
    private Date expenseDate;
    private  String expenseDesc;
    private String expenseState;

    private String operate;
    //报销单明细
    private int[] costIds;
    private float[] detailMoneys;
    private String[] detailDescs;

    public String getOperate() {
        if(expenseState.equals("0")){
            operate="<a href='expense/updateExpense?expenseId="+this.getExpenseId()+"'>修改</a>";
        }else {
            operate="<a href='expense/showExpenseDetails?expenseId="+this.getExpenseId()+"'>查看详情</a>";
        }
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public float getExpenseTotal() {
        return expenseTotal;
    }

    public void setExpenseTotal(float expenseTotal) {
        this.expenseTotal = expenseTotal;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getExpenseDesc() {
        return expenseDesc;
    }

    public void setExpenseDesc(String expenseDesc) {
        this.expenseDesc = expenseDesc;
    }

    public String getExpenseState() {
        return expenseState;
    }

    public void setExpenseState(String expenseState) {
        this.expenseState = expenseState;
    }

    public int[] getCostIds() {
        return costIds;
    }

    public void setCostIds(int[] costIds) {
        this.costIds = costIds;
    }

    public float[] getDetailMoneys() {
        return detailMoneys;
    }

    public void setDetailMoneys(float[] detailMoneys) {
        this.detailMoneys = detailMoneys;
    }

    public String[] getDetailDescs() {
        return detailDescs;
    }

    public void setDetailDescs(String[] detailDescs) {
        this.detailDescs = detailDescs;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", userId=" + userId +
                ", expenseName='" + expenseName + '\'' +
                ", expenseTotal=" + expenseTotal +
                ", expenseDate=" + expenseDate +
                ", expenseDesc='" + expenseDesc + '\'' +
                ", expenseState='" + expenseState + '\'' +
                ", costIds=" + Arrays.toString(costIds) +
                ", detailMoneys=" + Arrays.toString(detailMoneys) +
                ", detailDescs=" + Arrays.toString(detailDescs) +
                '}';
    }
}
