package com.coding.kafka.handler;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 */
@Component
@Slf4j
public class KafkaMessageHandler {

    /**
     * 使用默认的Factory, 同时，自动提交offset
     * @param record
     */
    @KafkaListener(topics = "test_topic",
      groupId = "consumer_test_03")
    public void handleMessageUsingDefaultFactory(ConsumerRecord record) {
        try {
            log.info("Thread {} 收到消息: 消息 {}", Thread.currentThread().getId(), record);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     *
     * @param record
     * @param acknowledgment
     */
    @KafkaListener(topics = "test_topic",
      containerFactory = "ackContainerFactory",
      groupId = "consumer_test_02")
    public void handleMessage(ConsumerRecord record,
                              Acknowledgment acknowledgment) {
        try {
            log.info("Thread {} 收到消息: 消息 {}", Thread.currentThread().getId(), record);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }

    @KafkaListener(topics = "test_topic",
      containerFactory = "ackBatchContainerFactory",
      groupId = "log_consumer_test_01")
    public void handleBatchMessage(List<ConsumerRecord> records,
                              Acknowledgment acknowledgment) {
        try {
            log.info("Thread {} 收到消息: 消息个数 {}", Thread.currentThread().getId(), records.size());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }
}
