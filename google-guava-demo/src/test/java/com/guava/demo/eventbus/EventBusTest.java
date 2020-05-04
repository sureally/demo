package com.guava.demo.eventbus;



import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventBusTest {

  static class EventListener {
    @Subscribe
    @AllowConcurrentEvents
    public void subscribeEvent1(String event) throws InterruptedException {
      Thread.sleep(1000);
      System.out.println(Thread.currentThread() + " subscribe 1: receive " + event + ": " + System.currentTimeMillis());
    }

    @Subscribe
    @AllowConcurrentEvents
    public void subscribeEvent2(String event) throws InterruptedException {
      Thread.sleep(1000);
      System.out.println(Thread.currentThread() + " subscribe 2: receive " + event + ": " + System.currentTimeMillis());
    }

    @Subscribe
    @AllowConcurrentEvents
    public void subscribeEvent3(String event) throws InterruptedException {
      Thread.sleep(1000);
      System.out.println(Thread.currentThread() + " subscribe 3: receive " + event + ": " + System.currentTimeMillis());
    }

    @Subscribe
    public void subscribeIntegerEvent(Integer integer) {
      System.out.println(Thread.currentThread() + " subscribe integer event: receive " + integer);
    }
  }

  private static EventBus eventBus = new com.google.common.eventbus.AsyncEventBus(Executors.newFixedThreadPool(1));
  private static EventListener eventListener = new EventListener();

  static {
    eventBus.register(eventListener);
  }

  @Test
  public void postTest() throws InterruptedException {
    eventBus.post("test event 1");
    eventBus.post("test event 2");
    eventBus.post("test event 3");
    Thread.sleep(100000);
  }


}
