package com.guava.demo.concurrency;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @Author shu wj
 * @Date 2020/6/12 19:57
 * @Description
 */
public class RateLimiterDemo {
  static RateLimiter limiter = RateLimiter.create(2.0);

  public static class Task implements Runnable {

    @Override
    public void run() {
      System.out.println(System.currentTimeMillis());
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < 50; i++) {
//      limiter.acquire(); // 阻塞
      if (!limiter.tryAcquire()) {
        // 丢弃请求
        continue;
      }
      new Thread(new Task()).start();
    }
  }
}
