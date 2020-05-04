package com.guava.demo.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Lists;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImmutableTest {

  @Test
  public void jdkImmutableNotSafeTest() {
    List<String> values = Lists.newArrayList("a", "b");
    List<String> unmodifiableValues = Collections.unmodifiableList(values);
    List<String> immutableList = ImmutableList.copyOf(values);

    assertEquals(values, unmodifiableValues);
    assertEquals(values, immutableList);
    values.add("c");
    assertEquals(values, unmodifiableValues);
    assertNotEquals(values, immutableList);
  }

  @Test
  public void buildImmutableTest() {
  }

  @Test
  public void arrayCopyOfTest() {
    @AllArgsConstructor
    class Model {
      String a;
      String b;
    }
    Model[] models = {new Model("a", "b"), new Model("c", "d")};
    Model[] copy = Arrays.copyOf(models, 2);
    List<Model> immutableList = ImmutableList.copyOf(models);

    assertArrayEquals(copy, models);
    assertArrayEquals(models, immutableList.toArray());
    models[0].a = "a1";
    assertArrayEquals(copy, models);
    // 改变集合中对象的属性，不可变集合中对应对象的属性也会改变
    assertArrayEquals(models, immutableList.toArray());
  }

  @Test
  public void copyOfTest() {
    List<String> values = ImmutableList.of("a", "b", "c");
    List<String> immutableValues1 = ImmutableList.copyOf(values);
    // 显示拷贝
    List<String> immutableValue2 = ImmutableList.copyOf(values.subList(0, 2));
  }
}
