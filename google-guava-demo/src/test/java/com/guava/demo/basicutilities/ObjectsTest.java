package com.guava.demo.basicutilities;

import java.util.ArrayList;
import java.util.Comparator;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectsTest {

  @Test
  public void equalsTest() {
    String a = new String("a");
    String b = new String("a");
    assertNotSame(a, b);
    assertEquals(a, b);
    assertEquals(a, b);
  }

  @Test
  public void hashCodeTest() {
    @Data
    @AllArgsConstructor
    class Inner {
      String field1;
      String field2;
    }
    Inner sample = new Inner("a", null);
    long hash = Objects.hashCode(sample.getField1(), sample.getField2());
    long hash_jdk = java.util.Objects.hash(sample.getField1(), sample.getField2());

    assertEquals(hash, hash_jdk);
  }

  @Test
  public void toStringTest() {
    @Data
    @AllArgsConstructor
    class Inner {
      String field1;
      String field2;
    }
    Inner sample = new Inner("a", null);
    assertEquals("Inner(field1=a, field2=null)", sample.toString());
    assertEquals("Inner{x=1}", MoreObjects.toStringHelper(sample).add("x", "1").toString());
    assertEquals("sample{x=1}", MoreObjects.toStringHelper("sample").add("x", "1").toString());
    assertEquals("Inner{x=1}", MoreObjects.toStringHelper(Inner.class).add("x", "1").toString());
  }

  @Test
  public void compareToTest() {
    @Data
    @AllArgsConstructor
    class Person {
      private String lastName;
      private String firstName;
      private int zipCode;

      public int compareTo2(Person that) {
        return ComparisonChain.start()
          .compare(this.lastName, that.lastName, Ordering.natural().nullsLast())
          .compare(this.firstName, that.firstName)
          .compare(this.zipCode, that.zipCode)
          .result();
      }
    }

    Person person1 = new Person(null, "a", 2);
    Person person2 = new Person(null, "a", 1);
    Person person3 = new Person("a", "a", 2);

    ArrayList<Person> personList2 = Lists.newArrayList(person1, person2, person3);
    personList2.sort(Person::compareTo2);
  }
}
