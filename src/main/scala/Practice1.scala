package org.SparkStreaming

import org.apache.spark.sql.SparkSession

object Practice1 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("not decided")
      .getOrCreate()



    val number = spark.sparkContext.textFile("input/P.txt")

    val numbers =  number.flatMap(_.split("\\s+")).filter(_.matches("\\d+")).map(_.toInt)

    //getting the result

    //1.one way
    //val sum = numbers.reduce(_+_)

    //2.another way
//    val sum = numbers.sum()

    //3.another way

    var sum =0L
    for(num <-numbers.collect())
      sum = sum+num

    println(sum)
//    println(numbers.collect)

//    numbers.foreach(println)

  }
}
