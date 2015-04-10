package speedtestchart.datatype.speedtest.cn

import speedtestchart.datatype.GenericSpeedtestRecord

import scala.util.parsing.json.Parser


/**
 * Created by beenotung on 3/23/15.
 */
object SpeedtestCnRecord {
  def newInstance(code: String): SpeedtestCnRecord = {
    new Parser
    //TODO
    null
  }

  def decodeAll(raws: Array[String]): Array[SpeedtestCnRecord] = {
    null
  }

  def decode(raws: Array[String]): SpeedtestCnRecord = {
    null
  }
}

class SpeedtestCnRecord(ip: String, download: Double, upload: Double, location: String, os: String, browser: Browser, time: Long) {
  def toGenericSpeedtestRecord: GenericSpeedtestRecord = {
    //TODO
    null
  }
}

