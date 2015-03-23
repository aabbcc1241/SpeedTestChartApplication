import java.io.File

import speedtestchart.Fetcher

import scala.util.matching.Regex


/**
 * Created by beenotung on 3/23/15.
 */
object Test extends App{
  override def main(args: Array[String]) {
    for(file<-Fetcher.recursiveListFiles(new File("/home/beenotung/files"),new Regex(".html")))
      println(file.toString)
  }
}
