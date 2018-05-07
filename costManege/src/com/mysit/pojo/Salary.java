package com.mysit.pojo;

import java.util.Date;

/**
 * 工资实体
 */
public class Salary {
    private int salaryId;
    private int userId;
    private String userName;
    private Date salaryMonth;
    private Date salaryDate;
    private int salaryBasic;
    private int salaryComm;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public Date getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(Date salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

    public Date getSalaryDate() {
        return salaryDate;
    }

    public void setSalaryDate(Date salaryDate) {
        this.salaryDate = salaryDate;
    }

    public int getSalaryBasic() {
        return salaryBasic;
    }

    public void setSalaryBasic(int salaryBasic) {
        this.salaryBasic = salaryBasic;
    }

    public int getSalaryComm() {
        return salaryComm;
    }

    public void setSalaryComm(int salaryComm) {
        this.salaryComm = salaryComm;
    }


    @Override
    public String toString() {
        return "Salary{" +
                "salaryId=" + salaryId +
                ", salaryMonth=" + salaryMonth +
                ", salaryDay=" + salaryDate +
                ", salaryBasic=" + salaryBasic +
                ", salaryComm=" + salaryComm +
                '}';
    }
}
