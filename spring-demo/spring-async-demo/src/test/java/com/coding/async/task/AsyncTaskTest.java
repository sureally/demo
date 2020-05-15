package com.coding.async.task;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.coding.async.SpringAsyncApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author shu wj
 * @Date 2019/11/9 00:21
 * @Description
 */
@Slf4j
public class AsyncTaskTest extends SpringAsyncApplicationTest {

  @Autowired
  private AsyncTask asyncTask;

  @Test
  public void testTask() throws InterruptedException {
    asyncTask.async();
    asyncTask.async(2);
    Thread.sleep(1000L);
  }

  @Test
  public void testTaskException() throws InterruptedException {
    asyncTask.asyncForThrowException();
    Thread.sleep(1000L);
  }

  @Test
  public void testAsyncReturnFuture() throws ExecutionException, InterruptedException {
    CompletableFuture<String> task1Result = asyncTask.asyncReturnFuture("task1", 5);
    CompletableFuture<String> task2Result = asyncTask.asyncReturnFuture("task2", 10);
    CompletableFuture<String> task3Result = asyncTask.asyncReturnFuture("task3", 2);

    try {
      CompletableFuture.allOf(task1Result, task2Result, task3Result).get(6, TimeUnit.SECONDS);
    } catch (TimeoutException e) {
      e.printStackTrace();
    }

    log.info("{}", task1Result.isDone());
    log.info("{}", task2Result.isDone());
    log.info("{}", task3Result.isDone());
  }
}
