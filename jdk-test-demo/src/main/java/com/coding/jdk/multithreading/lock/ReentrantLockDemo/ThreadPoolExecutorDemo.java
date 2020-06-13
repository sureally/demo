package com.coding.jdk.multithreading.lock.ReentrantLockDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author shu wj
 * @Date 2020/6/13 15:14
 * @Description
 */
public class ThreadPoolExecutorDemo {

  public static class Task implements Runnable {
    private int a;
    private int b;

    public Task(int a, int b) {
      this.a = a;
      this.b = b;
    }

    @Override
    public void run() {
      double c = a / b;
      System.out.println(c);
    }
  }


  public static void main(String[] args) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    executorService.submit(new Task(1, 0));
    try {
    executorService.execute(new Task(1, 0));
    } catch (Exception e) {
      e.printStackTrace();
    }

    Future res = executorService.submit(new Task(1, 0));
    try {
      res.get();
    } catch (Exception e) {
      e.printStackTrace();
    }

    executorService.shutdown();
  }

}
