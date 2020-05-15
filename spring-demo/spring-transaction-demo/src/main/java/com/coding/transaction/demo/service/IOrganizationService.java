package com.coding.transaction.demo.service;

import com.coding.transaction.demo.entity.Employee;
import com.coding.transaction.demo.entity.EmployeeHealthInsurance;

/**
 * @Author shu wj
 * @Date 2020/5/12 22:51
 * @Description The service will perform Organization Level Operation like Employee joining and exit. It makes use of the EmployeeService and HealthInsuranceService
 */

public interface IOrganizationService {
  void joinOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance);

  void joinOrganizationWithTransaction(Employee employee, EmployeeHealthInsurance employeeHealthInsurance);

  void leaveOrganization(Employee employee, EmployeeHealthInsurance employeeHealthInsurance);
}
