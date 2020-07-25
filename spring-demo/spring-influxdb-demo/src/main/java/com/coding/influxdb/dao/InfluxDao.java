package com.coding.influxdb.dao;

import java.util.concurrent.TimeUnit;

import com.coding.influxdb.service.SavingDataDemo;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** @Author shu wj @Date 2020/7/4 23:50 @Description */
@Component
public class InfluxDao {

  @Autowired private InfluxDB influxDB;

  public void writeDemo() {
    Point.Builder builder = Point.measurement("engine_speed");
    builder.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS);

    builder.addField("xx", 100);

    influxDB.setDatabase("engine_data");
    influxDB.write(builder.build());
  }

  public void writeDemo(SavingDataDemo.Data data) {
    Point.Builder builder = Point.measurement("engine_data");
    builder.time(data.getTime(), TimeUnit.MILLISECONDS);

    data.getData().forEach(builder::addField);

    influxDB.setDatabase("engine_data");
    influxDB.write(builder.build());
  }
}
