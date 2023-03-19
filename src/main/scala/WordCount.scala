package org.SparkStreaming

import org.apache.spark.sql.SparkSession

object WordCount {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("WordCounting")
      .getOrCreate()

    val sc = spark.sparkContext

    val file = sc.textFile("input/P.txt")

    val words = file.flatMap(_.split("\\s+")).filter(_.matches("\\w+"))

    val word = words.map(data =>(data,1))

    val rdd = word.reduceByKey(_ + _)
//    rdd.foreach(println)

    //swaping and sorting the data now

    val rdd2 = rdd.sortByKey()
    var k =1;
    for(num <-rdd2){
      println(k + " " + num)
      k = k+1
    }

    val count = rdd2.count()
    println(count)

    val countWords = rdd2.reduce((a,b) => (a._1,a._2+b._2))
    println(countWords._2)

    // swaping data
    val rdd3 = rdd2.map(data => (data._2,data._1))

    rdd3.foreach(println)

  }

}
