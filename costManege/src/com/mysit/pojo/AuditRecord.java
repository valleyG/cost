package com.mysit.pojo;

import java.util.Date;

/**
 * 审核记录实体
 */
public class AuditRecord {
    private int auditId;
    private int userId;
    private String userName;
    private int expenseId;
    private String auditSugg;
    private String auditState;
    private String auditStateName;
    private Date auditDate;

    public int getAuditId() {
        return auditId;
    }

    public void setAuditId(int auditId) {
        this.auditId = auditId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getAuditSugg() {
        return auditSugg;
    }

    public void setAuditSugg(String auditSugg) {
        this.auditSugg = auditSugg;
    }

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }

    public String getAuditStateName() {
        return auditStateName;
    }

    public void setAuditStateName(String auditSateName) {
        this.auditStateName = auditSateName;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    @Override
    public String toString() {
        return "AuditRecord{" +
                "auditId=" + auditId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", expenseId=" + expenseId +
                ", auditSugg='" + auditSugg + '\'' +
                ", auditState='" + auditState + '\'' +
                ", auditSateName='" + auditStateName + '\'' +
                ", auditDate=" + auditDate +
                '}';
    }
}
