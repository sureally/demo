package com.caffeine.demo;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.Scheduler;

/** @Author shu wj @Date 2020/7/15 00:43 @Description
 *
 * 设计思想，在不使用分布式缓存的基础上，使用内存缓存 + 数据库。 内存查不到再去数据库查找，同时设定内存中的淘汰时间。
 *
 * */
public class CaffeineTest {
  public static void main(String[] args) throws InterruptedException {

    ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(2);

    LoadingCache<String, String> graphs =
        Caffeine.newBuilder()
            .maximumSize(10_000)
            .expireAfterWrite(Duration.ofSeconds(2))
            .removalListener(
                (String key, String graph, RemovalCause cause) ->
                    System.out.printf("Key %s was removed (%s)\n", key, cause))
            .scheduler(
                Scheduler.guardedScheduler(
                    Scheduler.forScheduledExecutorService(newScheduledThreadPool)))
            .build(key -> Integer.valueOf(new Random().nextInt(1000)).toString());

    System.out.println(graphs.get("100"));
    System.out.println(graphs.get("101"));
    System.out.println(graphs.get("102"));

    Thread.sleep(5000);
    System.out.println(graphs.get("100"));

    Thread.sleep(50000);
  }
}
