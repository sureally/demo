package com.coding.jdk.multithreading.lock.ReentrantLockDemo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** @Author shu wj @Date 2020/6/13 15:25 @Description */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {
  public TraceThreadPoolExecutor(
      int corePoolSize,
      int maximumPoolSize,
      long keepAliveTime,
      TimeUnit unit,
      BlockingQueue<Runnable> workQueue) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
  }

  @Override
  public void execute(Runnable command) {
    super.execute(wrap(command, clientTrace(), Thread.currentThread().getName()));
  }

  @Override
  public Future<?> submit(Runnable task) {
    return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
  }

  private Exception clientTrace() {
    return new Exception("client stack trace");
  }

  private Runnable wrap(final Runnable task, final Exception clientStack, String clientThreadName) {
    return new Runnable() {
      @Override
      public void run() {
        try {
          task.run();
        } catch (Exception e) {
          clientStack.printStackTrace();
          throw e;
        }
      }
    };
  }

  public static void main(String[] args) {
     ThreadPoolExecutor threadPoolExecutor = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

     for (int i = 0; i < 1; i++) {
       threadPoolExecutor.execute(new ThreadPoolExecutorDemo.Task(1, 0));
     }
  }
}
