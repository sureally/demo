package com.guava.demo.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.ListenableFutureTask;
import org.junit.jupiter.api.Test;

class ListenableFutureTest {

  @Test
  public void listenableFutureTest() throws ExecutionException, InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(3);
    ListenableFutureTask<String> task = ListenableFutureTask.create(new Callable<String>() {
      @Override
      public String call() throws Exception {
        System.out.println("This is callable");
        Thread.sleep(1000);
        return "null";
      }
    });

    task.addListener(new Runnable() {
      @Override
      public void run() {
        System.out.println("This is listener");
      }
    }, executor);

    new Thread(task).start();

    Thread.sleep(10000);
  }

}