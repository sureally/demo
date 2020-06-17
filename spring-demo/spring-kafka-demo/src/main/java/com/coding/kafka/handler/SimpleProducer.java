package com.coding.kafka.handler;

import javax.annotation.Resource;
import java.text.MessageFormat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleProducer {

  @Resource
  private KafkaTemplate<Object, Object> kafkaTemplate;

  /**
   * 使用KafkaTemplate向Kafka推送数据
   *
   * @param topicName topic
   * @param data
   */
  public void sendMessage(String topicName, String data) {
    try {
      kafkaTemplate.send(topicName, data, data);
    } catch (Exception e) {
      log.error(MessageFormat.format("推送数据出错，topic:{0},data:{1}", topicName, data), e);
    }
  }
}
