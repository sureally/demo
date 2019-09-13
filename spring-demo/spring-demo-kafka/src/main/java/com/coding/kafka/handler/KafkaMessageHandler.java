package com.coding.kafka.handler;

import java.util.List;

import com.coding.kafka.constants.KafkaConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 */
@Component
@Slf4j
public class KafkaMessageHandler {

    @KafkaListener(topics = KafkaConstants.TOPIC_TEST, containerFactory = "ackContainerFactory")
    public void handleMessage(ConsumerRecord record,
                              Acknowledgment acknowledgment) {
        try {
            log.info("收到消息: 消息 {}", record);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaConstants.TOPIC_BATCH_TEST, containerFactory = "ackBatchContainerFactory")
    public void handleBatchMessage(List<ConsumerRecord> records,
                              Acknowledgment acknowledgment) {
        try {
            log.info("收到消息: 消息个数{}", records.size());
            records.forEach(record -> {
                String message = (String) record.value();
                log.info("收到消息: 消息 {}", record);
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }
}
