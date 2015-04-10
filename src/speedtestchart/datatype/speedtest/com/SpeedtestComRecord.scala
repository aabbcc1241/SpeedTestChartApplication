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

    raws.indices.foreach(i => {
      //println("checking(" + i + "): " + raws(i))
      if (raws(i) == "")
        if (start == -1)
          start = i
        else {
          //println(raws(i))
          end = i
          val array: Array[String] = Array.tabulate[String](end - start + 1)(j => raws(j + start))
          result += decode(array)
          start = -1
        }
    }
    )
    result.filter(p => p != null).toArray
  }

  def decode(raw: Array[String]): SpeedtestComRecord = {
    //TODO
    //println(raw.length)
    if (raw.length < 10) {
      //println("discarded record")
      return null
    }
    //println("building record")
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
                         uploadSpeed: String,
                         downloadSpeed: String,
                         time: String,
                         ispName: String,
                         testServer: String,
                         region: String)
  extends GenericSpeedtestRecord(raw, uploadSpeed, downloadSpeed, time, ispName, testServer) {
}
