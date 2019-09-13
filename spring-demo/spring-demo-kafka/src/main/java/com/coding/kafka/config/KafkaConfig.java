package com.coding.kafka.config;

import com.coding.kafka.constants.KafkaConstants;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

/**
 * @ClassName com.coding.kafka.config.KafkaConfig
 * @Desciption
 * @Author Shu WJ
 * @DateTime 2019-09-13 15:00
 * @Version 1.0
 **/
@Configuration
@EnableConfigurationProperties({KafkaProperties.class})
@EnableKafka
@AllArgsConstructor
public class KafkaConfig {
  private final KafkaProperties kafkaProperties;

  // 默认
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
    factory.setConsumerFactory(consumerFactory());
    factory.setConcurrency(KafkaConstants.SINGLE_CONCURRENCY);
    factory.setBatchListener(true);
    factory.getContainerProperties().setPollTimeout(3000);
    return factory;
  }

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    return new DefaultKafkaConsumerFactory<String, String>(kafkaProperties.buildConsumerProperties());
  }

  // 批量
  @Bean("ackBatchContainerFactory")
  public ConcurrentKafkaListenerContainerFactory<String, String> ackBatchContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
    factory.setConsumerFactory(consumerFactory());
    factory.setBatchListener(true);
    // ack 的模式为手动提交 offset
    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
    factory.setConcurrency(KafkaConstants.SINGLE_CONCURRENCY);
    return factory;
  }

  // 单条消费
  @Bean("ackContainerFactory")
  public ConcurrentKafkaListenerContainerFactory<String, String> ackContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
    factory.setConsumerFactory(consumerFactory());
    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
    factory.setConcurrency(KafkaConstants.SINGLE_CONCURRENCY);
    return factory;
  }
}
