package com.coding.jdk.multithreading.lock.ReentrantLockDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author shu wj
 * @Date 2020/6/8 22:17
 * @Description
 */
public class SemapDemo implements Runnable {
  final Semaphore semaphore = new Semaphore(5);

  @Override
  public void run() {
    try {
      semaphore.acquire();
      Thread.sleep(2000);
      System.out.println(Thread.currentThread().getId() + ": done!");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally{
      semaphore.release();
    }
  }


  public static void main(String[] args) {
    ExecutorService exec = Executors.newFixedThreadPool(20);
    final SemapDemo demo = new SemapDemo();
    for (int i = 0; i < 20; i++) {
      exec.submit(demo);
    }
  }
}
