package com.coding.jdk.multithreading.lock.ReentrantLockDemo;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author shu wj
 * @Date 2020/6/8 23:37
 * @Description
 */
public class LockSupportIntDemo {
  public final static Object u = new Object();
  static ChangeObjectThread t1 = new ChangeObjectThread("t1");
  static ChangeObjectThread t2 = new ChangeObjectThread("t2");

  public static class ChangeObjectThread extends Thread {
    public ChangeObjectThread(String name) {
      super.setName(name);
    }

    @Override
    public void run() {
      synchronized (u) {
        System.out.println("in " + getName());
        LockSupport.park(); // 当中断了此方法后，会马上响应中断，并返回。
        if (Thread.interrupted()) {
          System.out.println(getName() + "被中断了");
        }
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    t1.start();
    Thread.sleep(100);
    t2.start();
    t1.interrupt();

    LockSupport.unpark(t2); // t1 返回后在外面等待的t2才可以进入临界区。
  }
}
