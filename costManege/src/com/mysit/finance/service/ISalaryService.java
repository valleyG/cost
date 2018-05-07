package com.mysit.finance.service;

import com.mysit.pojo.Salary;
import com.mysit.pojo.SalaryChart;

import java.util.List;

public interface ISalaryService {
    boolean addSalary(Salary salary);
    List<Salary> querySalaryList(Salary salary);
    List<SalaryChart> querySalaryChart();
}
