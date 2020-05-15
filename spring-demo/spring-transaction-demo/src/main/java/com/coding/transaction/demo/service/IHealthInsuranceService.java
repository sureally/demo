package com.coding.transaction.demo.service;

import com.coding.transaction.demo.entity.EmployeeHealthInsurance;

/**
 * @Author shu wj
 * @Date 2020/5/15 20:31
 * @Description
 */
public interface IHealthInsuranceService {
  void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance);

  void deleteEmployeeHealthInsuranceById(String empid);
}
