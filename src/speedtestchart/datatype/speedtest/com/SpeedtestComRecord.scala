package speedtestchart.datatype.speedtest.com

import speedtestchart.datatype.GenericSpeedtestRecord

import scala.collection.mutable.ArrayBuffer

/**
 * Created by hkpu on 2/4/2015.
 */

object SpeedtestComRecord {
  def decodeAll(raws: Array[String]): Array[SpeedtestComRecord] = {
    val result = new ArrayBuffer[SpeedtestComRecord]()
    var start = -1
    var end = -1
    raws.indices.foreach(i =>
      if (raws(i) == " ")
        if (start == -1) start = i
        else {
          end = i
          var array: Array[String] = Array.fill[String](end - start)("")
          raws.copyToArray(array, start, array.length)
          result += decode(array)
          start = -1
        }
    )
    result.toArray
  }

  def decode(raw: Array[String]): SpeedtestComRecord = {
    //TODO
    val uploadSpeed = 0
    val downloadSpeed = 0
    val time = System.currentTimeMillis()
    val ispName = ""
    val testServer = ""
    val region = raw(2)
    new SpeedtestComRecord(raw.toVector.toString(), uploadSpeed, downloadSpeed, time, ispName, testServer, region)
  }
}

class SpeedtestComRecord(raw: String,
                         uploadSpeed: Double,
                         downloadSpeed: Double,
                         time: Long,
                         ispName: String,
                         testServer: String,
                         region: String)
  extends GenericSpeedtestRecord(raw, uploadSpeed, downloadSpeed, time, ispName, testServer) {

}
