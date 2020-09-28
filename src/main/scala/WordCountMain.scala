import java.time.LocalDateTime

import org.apache.spark.{SparkConf, SparkContext}

import scala.sys.exit

object WordCountMain {
  def main(args: Array[String]) = {
    if(args == null || args.length < 1) {
      println("The input file argument is missing !")
      exit(1)
    }
    val currentTime: String = LocalDateTime.now().toString().replace(':', '-')
    val inputFile = args(0)
    val outputFolder = inputFile + "_output_" + currentTime
    //val conf = new SparkConf().setAppName("WordCount by Cheikna and Faizaan").setMaster("local")
    //val sc = new SparkContext(conf);
    val sc = SparkContext.getOrCreate()
    val textFile = sc.textFile(inputFile)
    val counts = textFile.flatMap(line => line.split("[ ,.;:]"))
      .map(word => (word.toLowerCase(), 1))
      .reduceByKey(_ + _)
    println("Number of distinct words : " + counts.count())
    counts.saveAsTextFile(outputFolder)
  }
}
