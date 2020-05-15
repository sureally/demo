package com.coding.transaction.demo.service;

import javax.transaction.Transactional;

import com.coding.transaction.demo.entity.Employee;
import com.coding.transaction.demo.entity.EmployeeHealthInsurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** @Author shu wj @Date 2020/5/15 20:47 @Description */
@Service
public class IOrganizationServiceImpl implements IOrganizationService {

  @Autowired private EmployeeServiceImpl employeeService;

  @Autowired private HealthInsuranceServiceImpl healthInsuranceService;

  @Override
  public void joinOrganization(
      Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
    employeeService.insertEmployee(employee);

    if (employee.getEmpId().equals("emp2")) {
      throw new RuntimeException("thowing exception to test transaction rollback");
    }

    healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance);
  }

  @Override
  @Transactional
  public void joinOrganizationWithTransaction(Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
    employeeService.insertEmployee(employee);

    if (employee.getEmpId().equals("emp2")) {
      throw new RuntimeException("thowing exception to test transaction rollback");
    }

    healthInsuranceService.registerEmployeeHealthInsurance(employeeHealthInsurance);
  }

  @Override
  public void leaveOrganization(
      Employee employee, EmployeeHealthInsurance employeeHealthInsurance) {
    employeeService.deleteEmployById(employee.getEmpId());
    healthInsuranceService.deleteEmployeeHealthInsuranceById(employeeHealthInsurance.getEmpId());
  }
}
