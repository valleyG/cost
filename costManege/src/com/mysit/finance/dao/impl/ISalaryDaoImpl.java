package com.mysit.finance.dao.impl;

import com.mysit.finance.dao.ISalaryDao;
import com.mysit.pojo.Salary;
import com.mysit.pojo.SalaryChart;
import com.mysit.utils.C3p0Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ISalaryDaoImpl implements ISalaryDao {
    @Override
    public int addSalary(Salary salary) {
        String sql="insert into t_salary_record(userId,salaryMonth,salaryDate,salaryBasic,salaryComm) values(?,?,now(),?,?)";
        try {
            int rows;
            rows = C3p0Util.update(sql,salary.getUserId(),salary.getSalaryMonth(),salary.getSalaryBasic(),salary.getSalaryComm());
            return  rows;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Salary> querrySalaryList(Salary salary) {
        StringBuffer sql = new StringBuffer("select tsr.*,userName from t_user tu,t_salary_record tsr where tu.userId = tsr.userId");
        List<Object> params=new ArrayList<>();
        if (salary.getUserId()!=0&&!"".equals(salary.getUserId())) {
            sql.append(" and tsr.userId = ?");
            params.add(salary.getUserId());
        }
        if (salary.getUserName()!=null&&!"".equals(salary.getUserName())) {
            sql.append(" and userName like ?");
            params.add("%"+salary.getUserName()+"%");
        }
        if (salary.getSalaryMonth()!=null&&!"".equals(salary.getSalaryMonth())) {
            sql.append(" and salaryMonth = ?");
            params.add(salary.getSalaryMonth());
        }
        try {
            return C3p0Util.queryList(sql.toString(), Salary.class,params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SalaryChart> querySalaryChat() {
        String sql = "select salaryMonth,sum(salaryBasic) as salaryBasicTotal,sum(salaryComm) as salaryCommTotal from t_salary_record group by salaryMonth";
        try {
            return C3p0Util.queryList(sql, SalaryChart.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
