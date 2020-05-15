package com.coding.transaction.demo;

import com.coding.transaction.demo.dao.EmployeeHealthInsuranceRepository;
import com.coding.transaction.demo.dao.EmployeeRepository;
import com.coding.transaction.demo.entity.Employee;
import com.coding.transaction.demo.entity.EmployeeHealthInsurance;
import com.coding.transaction.demo.service.IOrganizationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTransactionApplicationTest {

  @Autowired private IOrganizationService organizationService;

  @Autowired private EmployeeHealthInsuranceRepository employeeHealthInsuranceRepository;

  @Autowired private EmployeeRepository employeeRepository;

  @Test
  public void normalTest() {
    Employee emp = new Employee();
    emp.setEmpId("emp1");
    emp.setEmpName("emp1");
    EmployeeHealthInsurance employeeHealthInsurance = new EmployeeHealthInsurance();

    employeeHealthInsurance.setEmpId("emp1");
    employeeHealthInsurance.setHealthInsuranceSchemeName("premium");
    employeeHealthInsurance.setCoverageAmount(20000);

    organizationService.joinOrganization(emp, employeeHealthInsurance);

    Assert.assertTrue(employeeRepository.findById("emp1").isPresent());
    Assert.assertTrue(employeeHealthInsuranceRepository.findById(1).isPresent());
  }

  @Test
  public void exceptionWithoutTransactionTest() {
    String empId = "emp2";
    Employee emp = new Employee();
    emp.setEmpId(empId);
    emp.setEmpName(empId);
    EmployeeHealthInsurance employeeHealthInsurance = new EmployeeHealthInsurance();

    employeeHealthInsurance.setEmpId(empId);
    employeeHealthInsurance.setHealthInsuranceSchemeName("premium");
    employeeHealthInsurance.setCoverageAmount(20000);
    try {
      organizationService.joinOrganization(emp, employeeHealthInsurance);
    } catch (Exception e) {
      e.printStackTrace();
    }
    Assert.assertTrue(employeeRepository.findById(empId).isPresent());
    Assert.assertTrue(employeeHealthInsuranceRepository.findAll().isEmpty());
  }

  @Test
  public void transactionTest() {
    String empId = "emp2";
    Employee emp = new Employee();
    emp.setEmpId(empId);
    emp.setEmpName(empId);
    EmployeeHealthInsurance employeeHealthInsurance = new EmployeeHealthInsurance();

    employeeHealthInsurance.setEmpId(empId);
    employeeHealthInsurance.setHealthInsuranceSchemeName("premium");
    employeeHealthInsurance.setCoverageAmount(20000);

    try {
      organizationService.joinOrganizationWithTransaction(emp, employeeHealthInsurance);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertTrue(employeeRepository.findAll().isEmpty());
    Assert.assertTrue(employeeHealthInsuranceRepository.findAll().isEmpty());
  }
}
