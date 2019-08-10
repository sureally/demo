package com.savepoint.demo

import org.apache.flink.runtime.state.filesystem.FsStateBackend
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.environment.CheckpointConfig.ExternalizedCheckpointCleanup
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.runtime.operators.windowing.WindowOperator

/**
  * @ObjectName com.savepoint.demo.SavepointJob
  * @Desciption
  * @Author Shu WJ
  * @DateTime 2019-07-29 23:14
  * @Version 1.0
  **/
object SavepointJob {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    // checkpoint周期
    env.enableCheckpointing(1000)
    // checkpoint 保存路径
    env.setStateBackend(new FsStateBackend("file:////Users/shushu/IdeaProjects/demo/flink-demo/savepoint-demo/data/flink"))
    val config = env.getCheckpointConfig
    // checkpoint mode
    config.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)

    // checkpoint执行有效期：要么1min完成 要么1min放弃
    config.setCheckpointTimeout(60000)

    // 确保checkpoint时间空闲间隔500ms
    config.setMinPauseBetweenCheckpoints(500)

    // 允许同一时间只存在一个checkpoint
    config.setMaxConcurrentCheckpoints(1)

    // job cancellation启用保留的外部检查点
    config.enableExternalizedCheckpoints(ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION)

    // This determines if a task will be failed if an error occurs in the execution of the task’s checkpoint procedure.
    config.setFailOnCheckpointingErrors(true)

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
