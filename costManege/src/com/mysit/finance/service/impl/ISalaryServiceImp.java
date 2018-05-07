package com.mysit.finance.service.impl;

import com.mysit.finance.dao.ISalaryDao;
import com.mysit.finance.dao.impl.ISalaryDaoImpl;
import com.mysit.finance.service.ISalaryService;
import com.mysit.pojo.Salary;
import com.mysit.pojo.SalaryChart;

import java.util.List;

public class ISalaryServiceImp implements ISalaryService {
    ISalaryDao salaryDao = new ISalaryDaoImpl();

    @Override
    public boolean addSalary(Salary salary) {
        int rows = salaryDao.addSalary(salary);
        if (rows>0) {
            return true;
        }else {
            return  false;
        }
    }

    @Override
    public List<Salary> querySalaryList(Salary salary) {
        return salaryDao.querrySalaryList(salary);
    }

    @Override
    public List<SalaryChart> querySalaryChart() {
        return salaryDao.querySalaryChat();
    }
}
