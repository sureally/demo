package com.exactlyonce.demo

import org.apache.flink.streaming.api.functions.sink.TwoPhaseCommitSinkFunction

/**
  * @ObjectName com.exactlyonce.demo.Application
  * @Desciption
  * @Author Shu WJ
  * @DateTime 2019-07-21 22:53
  * @Version 1.0
  **/
object Application {
  def main(args: Array[String]): Unit = {
    TwoPhaseCommitSinkFunction
    println("test")
  }

}
