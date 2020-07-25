package com.coding.influxdb.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.coding.influxdb.dao.InfluxDao;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author shu wj
 * @Date 2020/7/4 23:57
 * @Description
 */
@Service
public class SavingDataDemo {

  @Autowired
  private InfluxDao influxDao;

  public void readAndWrite() {

    influxDao.writeDemo();
  }

  public void readData(String path, String fileName) {
    File file = new File(path, fileName);
    long now = System.currentTimeMillis();
    try {
      List<String> lines = Files.readLines(file, Charset.forName("gb18030"));
      List<String> fieldNames = Lists.newArrayList();
      if (lines.size() > 0) {
        fieldNames.addAll(strToList.apply(lines.get(0)));
        lines.remove(0);
      }

      List<Data> datas = Lists.newArrayList();

      for (String line : lines) {
        Data data = new Data();
        List<String> apply = strToList.apply(line);
        for (int i = 0; i < apply.size(); i++) {
          String fv = apply.get(i);
          double fieldValue = Double.parseDouble(fv);
          if (i == 0) {
            data.setTime(now + Double.valueOf(fieldValue * 1000).longValue());
            continue;
          }
          data.getData().put(fieldNames.get(i), fieldValue);
        }

        datas.add(data);
      }

      for (Data data : datas) {
        influxDao.writeDemo(data);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @lombok.Data
  public static class Data {
    private Long time;
    private Map<String, Double> data = Maps.newHashMap();
  }

  Function<String, List<String>> strToList = (line) -> {
    return Arrays.asList(line.split("\\s+"));
  };

}
