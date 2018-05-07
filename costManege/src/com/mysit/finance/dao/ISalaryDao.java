package com.mysit.finance.dao;

import com.mysit.pojo.Salary;
import com.mysit.pojo.SalaryChart;

import java.util.List;

public interface ISalaryDao {
   int addSalary(Salary salary);
   List<Salary> querrySalaryList(Salary salary);
   List<SalaryChart> querySalaryChat();
}
