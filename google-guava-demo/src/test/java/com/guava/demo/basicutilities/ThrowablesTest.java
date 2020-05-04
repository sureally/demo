package com.guava.demo.basicutilities;

import com.google.common.base.Throwables;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThrowablesTest {

  @Test
  public void propagateIfPossibleTest() {
    Throwables.propagateIfPossible(null, null);

  }

}