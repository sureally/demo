package com.coding.jdk.multithreading.create;

/**
 * @Author shu wj
 * @Date 2019/12/12 23:23
 * @Description
 */
public class ImplementRunnable implements Runnable {
  @Override
  public void run() {
    System.out.println(String.format("This is ImplementRunnable thread: %s", Thread.currentThread()));
  }

  public static void main(String[] args) {
    System.out.println(String.format("Main thread start: %s", Thread.currentThread()));
    new Thread(new ImplementRunnable()).start();
    System.out.println(String.format("Main thread end: %s", Thread.currentThread()));
  }
}
