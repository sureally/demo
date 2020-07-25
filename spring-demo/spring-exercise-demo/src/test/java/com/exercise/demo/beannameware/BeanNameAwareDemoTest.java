package com.exercise.demo.beannameware;

import com.exercise.demo.ExerciseDemoApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BeanNameAwareDemoTest extends ExerciseDemoApplicationTest {

  @Autowired
  private BeanNameAwareDemo beanNameTest;

  @Test
  public void beanNameTest() {
    beanNameTest.logBeanName();
  }

}
