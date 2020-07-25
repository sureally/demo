package com.spark.demo;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

public final class JavaWordCount {
  private static final Pattern SPACE = Pattern.compile("");

  private static List<String> strings =
      Lists.newArrayList(
          "mesos://HOST:PORT 连接到指定的 Mesos 集群，需要指定端口。\n",
          "点击edit configuration，在左侧点击该项目。在右侧VM options中输入“-Dspark.master=local”，指示本程序本地单线程运行，再次运行即可。\n"
              + "\n");

  public static void main(String[] args) throws Exception {

    SparkSession spark = SparkSession.builder().appName("JavaWordCount").getOrCreate();

    JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());
    JavaRDD<String> lines = sc.parallelize(strings);

    JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator());

    JavaPairRDD<String, Integer> ones = words.mapToPair(s -> {
      System.out.println("测试: " + s);
      return new Tuple2<>(s, 1);
    });

    JavaPairRDD<String, Integer> counts = ones.reduceByKey((i1, i2) -> i1 + i2);

    List<Tuple2<String, Integer>> output = counts.collect();
    for (Tuple2<?, ?> tuple : output) {
      System.out.println("统计结果: " + tuple._1() + ": " + tuple._2());
    }
    spark.stop();
  }
}
