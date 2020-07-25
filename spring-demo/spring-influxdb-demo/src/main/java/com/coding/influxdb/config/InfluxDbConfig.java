package com.coding.influxdb.config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author shu wj
 * @Date 2020/7/4 23:50
 * @Description
 */
@Configuration
public class InfluxDbConfig {

  @Bean
  public InfluxDB influxDB() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.readTimeout(30, TimeUnit.SECONDS);

    InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086", builder);

    return influxDB;
  }
}
