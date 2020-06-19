package com.coding.kafka.handler;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleProducerTest {

  private final String topic = "test-for-kafka";

  private final RateLimiter rateLimiter = RateLimiter.create(20000);

  @Autowired
  private SimpleProducer simpleProducer;

  private static final String hdfsAudit = "";

  @Test
  public void mockData() {
    while (true) {
      rateLimiter.acquire(1);
      simpleProducer.sendMessage(topic, hdfsAudit + System.currentTimeMillis());
    }
  }
}
