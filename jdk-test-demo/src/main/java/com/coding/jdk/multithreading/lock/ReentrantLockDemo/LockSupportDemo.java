package com.coding.jdk.multithreading.lock.ReentrantLockDemo;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author shu wj
 * @Date 2020/6/8 23:37
 * @Description
 */
public class LockSupportDemo {
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
        LockSupport.park();
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    t1.start();
    Thread.sleep(100);
    t2.start();

    // 注意，这里无法保证 unpark() 发生在 park() 之后，但是，还是能正常结束。
    LockSupport.unpark(t1);
    LockSupport.unpark(t2);

    t1.join();
    t2.join();
  }
}
