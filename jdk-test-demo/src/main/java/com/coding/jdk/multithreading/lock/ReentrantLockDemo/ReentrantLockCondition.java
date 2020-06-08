package com.coding.jdk.multithreading.lock.ReentrantLockDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author shu wj
 * @Date 2020/6/7 17:17
 * @Description
 */
public class ReentrantLockCondition implements Runnable {
  public static ReentrantLock lock = new ReentrantLock();
  public static Condition condition = lock.newCondition();
  @Override
  public void run() {

  }
}
