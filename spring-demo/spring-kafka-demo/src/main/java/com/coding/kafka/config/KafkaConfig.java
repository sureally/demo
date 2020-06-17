package com.coding.kafka.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
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
@EnableKafka
@AllArgsConstructor
public class KafkaConfig {

  private final KafkaProperties kafkaProperties;

  private final Integer SINGLE_CONCURRENCY = 1;

  private final Integer BATCH_CONCURRENCY = 4;

  /**
   * 默认，自动提交offset
   */
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

  /**
   * consumer factory
   * @return
   */
  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
  }

  /**
   * 批量消费，手动提交
   */
  @Bean("ackBatchContainerFactory")
  public ConcurrentKafkaListenerContainerFactory<String, String> ackBatchContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    factory.setBatchListener(true);
    // ack 的模式为手动提交 offset
    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
    // 可设定多线程消费，最大线程数为消费topic的分区数，也就是一个线程消费一个分区可达到最大的吞吐量
    factory.setConcurrency(BATCH_CONCURRENCY);
    return factory;
  }

  /**
   * 单条消费，手动提交
   */
  @Bean("ackContainerFactory")
  public ConcurrentKafkaListenerContainerFactory<String, String> ackContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
    factory.setBatchListener(false);
    factory.setConcurrency(SINGLE_CONCURRENCY);
    return factory;
  }

  /**
//   * KafkaTemplate
//   * @return
//   */
//  @Bean
//  public KafkaTemplate<Object, Object> kafkaTemplate(){
//    return new KafkaTemplate<Object, Object>(producerFactory(), true);
//  }
//
//  /**
//   * ProducerFactory
//   * @return
//   */
//  @Bean
//  public ProducerFactory<Object, Object> producerFactory(){
//    Map<String, Object> config = kafkaProperties.getProducer().buildProperties();
//    ProducerFactory<Object, Object> producerFactory = new DefaultKafkaProducerFactory<>(config);
//    return producerFactory;
//  }
}
