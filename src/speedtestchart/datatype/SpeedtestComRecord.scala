package speedtestchart.datatype

import java.security.Timestamp

/**
 * Created by hkpu on 2/4/2015.
 */

object SpeedtestComRecord {
  def decode(raw: String): GenericSpeedtestRecord = {
    //TODO
    val uploadSpeed=0
    val downloadSpeed=0
    val time=null
    val ispName=""
    val testServer=""
    new GenericSpeedtestRecord(raw, uploadSpeed, downloadSpeed, time, ispName, testServer)
  }
}