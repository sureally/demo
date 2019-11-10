package com.coding.async.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author shu wj
 * @Date 2019/11/7 22:26
 * @Description 异步任务模拟
 */
@Slf4j
@Component
public class AsyncTask {

  @Async
  public void async() {
    log.info("Doing async task: {}", Thread.currentThread());
  }

  @Async
  public void async(int taskId) {
    log.info("Doing async taskId is {}: {}", taskId, Thread.currentThread());
  }

  @Async
  public void asyncForThrowException() {
    throw new IllegalArgumentException("Something is worry!");
  }
}
