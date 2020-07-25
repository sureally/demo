package com.exercise.demo.beannameware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @Author shu wj
 * @Date 2020/7/23 00:39
 * @Description
 */
@Slf4j
@Component
public class BeanNameAwareDemo implements BeanNameAware {
  private String beanName;

  @Override
  public void setBeanName(String name) {
    this.beanName = name;
    log.info("{}", BeanFactoryUtils.originalBeanName(name));
  }

  public void logBeanName() {
    log.info("this bean name is: {}", this.beanName);
  }
}
