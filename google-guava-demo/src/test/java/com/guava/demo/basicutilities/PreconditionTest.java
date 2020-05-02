package com.guava.demo.basicutilities;

import com.google.common.base.Strings;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.jupiter.api.Test;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;
import static com.google.common.base.Preconditions.checkPositionIndexes;
import static com.google.common.base.Preconditions.checkState;
import static org.junit.jupiter.api.Assertions.*;

class PreconditionTest {

  @Test
  public void checkArgumentTest() {
    assertThrows(IllegalArgumentException.class, () -> checkArgument(false, "i应该大于0"), "");

    assertThrows(IllegalArgumentException.class, () -> checkArgument(false, "value of i is %s", "a"));
  }

  @Test
  public void checkNotNullTest() {
    assertThrows(NullPointerException.class, () -> checkNotNull(null, "%s 不能为空", "a"), "");
  }

  @Test
  public void checkStateTest() {
    assertThrows(IllegalStateException.class, () -> checkState(false, "state of i is not %s", "a"));
  }

  @Test
  public void checkElementIndexTest() {
    // checkElementIndex 是索引，所以指定的size 是 [0, size)，左闭右开
    checkElementIndex(0, 2, "某参数");
    assertThrows(IndexOutOfBoundsException.class, () -> checkElementIndex(-1, 2, "某参数"));
    assertThrows(IndexOutOfBoundsException.class, () -> checkElementIndex(2, 2, "某参数"));
  }

  @Test
  public void checkPositionIndexTest() {
    // checkPosition 指定的是位置，所以指定的size表示的区间是[0, size] 闭区间
    checkPositionIndex(2, 2, "某参数");
    assertThrows(IndexOutOfBoundsException.class, () -> checkPositionIndex(3, 2, "某参数"));
    assertThrows(IndexOutOfBoundsException.class, () -> checkPositionIndex(-1, 2, "某参数"));
  }

  @Test
  public void checkPositionIndexesTest() {
    checkPositionIndexes(2, 2, 2);
    assertThrows(IndexOutOfBoundsException.class, () -> checkPositionIndexes(3, 2, 2));
    assertThrows(IndexOutOfBoundsException.class, () -> checkPositionIndexes(-1, 2, 2));
  }
}