package com.coding.async.task;

import com.coding.async.SpringAsyncApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author shu wj
 * @Date 2019/11/9 00:21
 * @Description
 */
public class AsyncTaskTest extends SpringAsyncApplicationTest {

  @Autowired
  private AsyncTask asyncTask;

  @Test
  public void testTask() throws InterruptedException {
    asyncTask.async();
    Thread.sleep(1000L);
  }

  @Test
  public void testTaskException() throws InterruptedException {
    asyncTask.asyncForThrowException();
    Thread.sleep(1000L);
  }
}
