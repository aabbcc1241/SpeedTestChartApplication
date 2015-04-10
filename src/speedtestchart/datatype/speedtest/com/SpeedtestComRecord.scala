package speedtestchart.datatype.speedtest.com

import speedtestchart.datatype.GenericSpeedtestRecord

import scala.collection.mutable.ArrayBuffer

/**
 * Created by hkpu on 2/4/2015.
 */

object SpeedtestComRecord {
  def decodeAll(raws: Array[String]) = {
    val result = new ArrayBuffer[SpeedtestComRecord]()
    raws.foreach(raw => result += decode(raw))
  }

  def decode(raw: String): SpeedtestComRecord = {
    //TODO
    val uploadSpeed = 0
    val downloadSpeed = 0
    val time = null
    val ispName = ""
    val testServer = ""
    val region = ""
    new SpeedtestComRecord(raw, uploadSpeed, downloadSpeed, time, ispName, testServer, region)
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
