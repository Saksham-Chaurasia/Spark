package org.SparkStreaming

import org.apache.spark.sql.SparkSession

object CricketData {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[3]")
      .appName("Cricket")
      .getOrCreate()

    val sc = spark.sparkContext

    val rdd = sc.textFile("input/t20.csv")



//    rdd.collect().foreach(println)

//    println(rdd.getNumPartitions)

    val rdd2 = rdd.repartition(5)

//    rdd2.collect().foreach(println)

    // schema of the csv file
//    val schema = rdd.first()
//
//    println(rdd2.count())
//    println(schema)

// then it sort by with the column 0
    val rdd3 = rdd2.map(_.split(",")).sortBy(row=>row(0).toInt)

    





    // map returns a array of line in which the data is there
    //while flatmap flattens all the data in the line it gives the data which is split by in line or next line

    //to print the data which is in now rdd3 as a array of each line, so we have to print the inside of the array,

    // while using only rdd3.foreach(println) it only iterates over the new lines. not inside of it
    //so we first collect() all the data in new rdd3 array



    //it triggers an action .collect()
    //rdd3Array will be an array that contains all the elements of rdd3, where each element of the array will be an array of strings that corresponds to a comma-separated line in the original rdd2.
//    val rdd3array = rdd3.collect()
//
////    for (element<- rdd3array)
////      println(element.mkString(","))
//
//    for (row <- rdd3array) {
//      for (element <- row) {
//        print(element + " ")
//      }
//      println()
//    }


//    he inner loop is hidden inside the mkString() method
//    , which takes each element of the current array and concatenates them into a string separated by commas.
//
//      The concatenated string is printed to the console using println()

      //calculating which palyer have most runs





  }

}
