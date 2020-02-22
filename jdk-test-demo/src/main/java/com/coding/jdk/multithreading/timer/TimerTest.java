package com.coding.jdk.multithreading.timer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @Author shu wj
 * @Date 2020/1/13 01:21
 * @Description
 */

public class TimerTest {



  public static void main(String[] args) throws InterruptedException {

    while (true) {
      Timer timer1 = new Timer("self-timer2", false);
      timer1.schedule(new Task("2", timer1), TimeUnit.SECONDS.toMillis(1));
      Thread.sleep(100);

      // 需要调用cancel，否则，timer对象不会被gc
      timer1.cancel();;
    }


  }
}

class Task extends TimerTask {

  private String name;
  private Timer timer;

  public Task(String name, Timer timer) {
    this.name = name;
    this.timer = timer;
  }

  @Override
  public void run() {

    System.out.println(System.currentTimeMillis() + " " + name + " Running in: " + Thread.currentThread());
    try {
      Thread.sleep(20);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(System.currentTimeMillis() + " " + name + " closing in: " + Thread.currentThread());
    timer.cancel();
  }
}
