package com.coding.influxdb.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SavingDataDemoTest {

  @Autowired
  private SavingDataDemo savingDataDemo;

  @Test
  public void save() {
    savingDataDemo.readAndWrite();
  }

  @Test
  public void write() {
    savingDataDemo.readData("/Users/shushu/other/engine", "2016年10月11日16点11分24秒_LYZ_A.txt");
  }
}
