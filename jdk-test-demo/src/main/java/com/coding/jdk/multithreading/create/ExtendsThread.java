package com.coding.jdk.multithreading.create;

/**
 * @Author shu wj
 * @Date 2019/12/12 23:18
 * @Description 继承 Thread
 */
public class ExtendsThread extends Thread  {

  @Override
  public void run() {
    System.out.println(String.format("this is %s", Thread.currentThread()));
  }

  public static void main(String[] args) {
    new ExtendsThread().start();
    System.out.println(String.format("Main thread is end: %s", Thread.currentThread()));
  }
}
