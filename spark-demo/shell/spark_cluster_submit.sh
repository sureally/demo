#!/bin/bash
SHELL_DIR=$(cd `dirname $0`; pwd)
cd ${SHELL_DIR}

/usr/ndp/current/spark2_client/bin/spark-submit \
--class com.spark.demo.JavaWordCount \
--master yarn \
--deploy-mode client \
--executor-memory 2G \
--num-executors 2 \
--executor-cores 5 \
--driver-memory 2G \
--driver-cores 2 \
--queue test_cs_queue \
spark-demo-1.0-SNAPSHOT.jar
#--driver-java-options "-Dlog4j.configuration=log4j-driver.properties" \
#--conf spark.executor.extraJavaOptions="-Dlog4j.configuration=log4j-executor.properties" \
#--files ${SHELL_DIR}/log4j-driver.properties,${SHELL_DIR}/log4j-executor.properties
