package com.coding.jdk.multithreading.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

import sun.tools.jconsole.inspector.ThreadDialog;

/**
 * @Author shu wj
 * @Date 2019/12/14 20:48
 * @Description
 */
public class ImplementCallable implements Callable<String> {
  @Override
  public String call() throws Exception {
    System.out.println(String.format("This is ImplementCallable thread: %s", Thread.currentThread()));
    return "success";
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    System.out.println(String.format("Main thread start: %s", Thread.currentThread()));
    Executors.newCachedThreadPool().execute(new FutureTask<>((new ImplementCallable())));
    System.out.println(String.format("Main thread end: %s", Thread.currentThread()));
  }
}
