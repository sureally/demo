package com.coding.jdk.multithreading.lock.ReentrantLockDemo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author shu wj
 * @Date 2020/6/8 23:06
 * @Description
 */
public class CountDownLatchDemo implements Runnable {
  static final CountDownLatch end = new CountDownLatch(10);
  static final CountDownLatchDemo demo = new CountDownLatchDemo();

  @Override
  public void run() {
    try {
      Thread.sleep(new Random().nextInt(10) * 1000);
      System.out.println("check complete");
      end.countDown();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ExecutorService exec = Executors.newFixedThreadPool(10);
    try {
    for (int i = 0; i < 10; i++) {
      exec.submit(demo);
    }

    end.await();

    System.out.println("Fire!");
    } finally{
      exec.shutdown();
    }
  }
}
