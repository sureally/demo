package com.guava.demo.strings;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringsTest {

  @Test
  public void lenientBenchTest() {
    String template = "%s %s %s";
    String a = "Test";
    String b = "lenient";
    String c = "bench";

    int times = 1000000;
    Stopwatch stopwatch = Stopwatch.createUnstarted();
    stopwatch.start();
    for (int i = 0; i < times; i++) {
      usingLenient(template, a, b, c);
    }
    stopwatch.stop();
    System.out.println(times + "次 using lenient cost " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");

    stopwatch.reset();
    stopwatch.start();
    for (int i = 0; i < times; i++) {
      usingStringFormat(template, a, b, c);
    }
    stopwatch.stop();
    System.out.println(times + "次 using stringFormat cost " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
  }


  private void usingLenient(String template, String... args) {
    Strings.lenientFormat(template, args);
  }

  private void usingStringFormat(String template, String... args) {
    String.format(template, args);
  }
}