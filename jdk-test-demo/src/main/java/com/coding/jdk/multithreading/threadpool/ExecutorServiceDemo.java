package com.coding.jdk.multithreading.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

/** @Author shu wj @Date 2020/6/6 20:42 @Description */
public class ExecutorServiceDemo {

  private static Supplier<Callable<String>> newTask =
      () -> {
        return (Callable<String>)
            () -> {
              int sleepMillis = new Random().nextInt(10000);
              System.out.println(
                  "Starting running task "
                      + Thread.currentThread().getName()
                      + " and sleep "
                      + sleepMillis
                      + " millis");
              Thread.sleep(sleepMillis);
              if (sleepMillis > 5000) throw new RuntimeException();
              return "task: sleep " + sleepMillis + " millis";
            };
      };

  public static void executorServiceDemo() {
    ExecutorService executor = Executors.newFixedThreadPool(10);

    List<Callable<String>> tasks = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      tasks.add(newTask.get());
    }

    try {
      List<Future<String>> results = executor.invokeAll(tasks);

      System.out.println("completed invokedAll method");
      for (Future<String> res : results) {
        try {
          System.out.println(res.get());
        } catch (ExecutionException e) {
          e.printStackTrace();
        }
      }

    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      executor.shutdown();
    }
  }

  public static void main(String[] args) {
    executorServiceDemo();
  }
}
