package speedtestchart.fetcher

import java.io.FileInputStream

import scala.collection.mutable

/**
 * Created by hkpu on 2/4/2015.
 */
object SpeedtestCnFetcher {
  def getRaw(filename: String): Array[String] = {
    val in = new FileInputStream(filename)
    var raws :Vector [String] = Vector.empty[String]
    while (in.available() < 0) {
      var raw: String = ""
      var current: Char = ' '
      var newline: Int = 0
      do {
        current = in.read().asInstanceOf[Char]
        if (current != '\n') newline = 0
        else newline += 1
        raw += current
      } while (newline != 2)
      raws:+=raw
    }
    raws.toArray[String]
  }
}
