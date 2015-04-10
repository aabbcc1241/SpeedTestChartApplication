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
}

class SpeedtestCnRecord(ip: String, download: Double, upload: Double, location: String, os: String, browser: Browser, time: Long) {
  def toGenericSpeedtestRecord: GenericSpeedtestRecord = {
    //TODO
    null
  }
}

