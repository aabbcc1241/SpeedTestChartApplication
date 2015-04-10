package speedtestchart.datatype

/**
 * Created by hkpu on 2/4/2015.
 */

/**
 *
 * @param raw
 * multi-line string from file
 * @param uploadSpeed
 * bit per second
 * @param downloadSpeed
 * bit per second
 * @param time
 * time of the speed test record
 * @param ispName
 * name of the internet service provider
 * @param testServer
 * name of the speed test server
 */
class GenericSpeedtestRecord(val raw: String,
                             val uploadSpeed: Double,
                             val downloadSpeed: Double,
                             val time: Long,
                             val ispName: String,
                             val testServer: String) {
  override def toString: String = {
    Vector(uploadSpeed, downloadSpeed, time, ispName, testServer).toString()
  }
}
