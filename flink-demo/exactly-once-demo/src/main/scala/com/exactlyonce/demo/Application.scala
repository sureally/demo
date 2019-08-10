package com.exactlyonce.demo

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * @ObjectName com.exactlyonce.demo.Application
  * @Desciption
  * @Author Shu WJ
  * @DateTime 2019-07-21 22:53
  * @Version 1.0
  **/
object Application {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.socketTextStream("localhost", 9999)

    val counts = text.flatMap { _.toLowerCase.split("\\W+") filter { _.nonEmpty } }
      .map { (_, 1) }
      .keyBy(0)
      .timeWindow(Time.seconds(5))
      .sum(1)

    counts.print()



    env.execute("Window Stream WordCount")

  }

}
