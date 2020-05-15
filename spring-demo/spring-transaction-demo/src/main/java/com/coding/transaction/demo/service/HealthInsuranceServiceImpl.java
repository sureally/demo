package com.coding.transaction.demo.service;

import com.coding.transaction.demo.dao.EmployeeHealthInsuranceRepository;
import com.coding.transaction.demo.entity.EmployeeHealthInsurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author shu wj
 * @Date 2020/5/12 22:51
 * @Description The service will perform Employee Health Insurance Operations
 */
@Service
public class HealthInsuranceServiceImpl implements IHealthInsuranceService {

  @Autowired
  private EmployeeHealthInsuranceRepository employeeHealthInsuranceRepository;

  @Override
  public void registerEmployeeHealthInsurance(EmployeeHealthInsurance employeeHealthInsurance) {
    employeeHealthInsuranceRepository.save(employeeHealthInsurance);
  }

  @Override
  public void deleteEmployeeHealthInsuranceById(String empid) {
    employeeHealthInsuranceRepository.deleteByEmpId(empid);
  }
}
