package org.SparkStreaming
import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.expr
object SreatingWC {

  lazy val logger: Logger = Logger.getLogger(getClass.getName)
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[3]")
      .appName("Streaming Word Count")
      .config("spark.streaming.stopGracefullyOnShutdown", "true")
      .config("spark.sql.shuffle.partitions", 3)
      .getOrCreate()

    val linesDf = spark.readStream //stream Reader Dataframe
      .format("socket")
      .option("host","localhost")
      .option("port","9999")
      .load()


    val wordsDF = linesDf.select(expr("explode (split(value,' ')) as word"))
    val countsDF = wordsDF.groupBy("word").count()

    val wordCountQuery = countsDF.writeStream
      .format("console")

      .outputMode("complete") // update , append, complete
      .option("checkpintLocaiton","chk-point-dir")
      .start()


    logger.info("Listening to localhost:9999")
    wordCountQuery.awaitTermination()


    // now we use command prompt to connect with port which i mentioned
    // ncat -lk 9999
    // now we can pass the commands

  }
}