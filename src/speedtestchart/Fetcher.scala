package speedtestchart

/**
 * Created by beenotung on 3/23/15.
 */

import java.io.File

import scala.util.matching.Regex

object Fetcher {
  def recursiveListFiles(f: File, r: Regex): Array[File] = {
    val these = f.listFiles
    val good = these.filter(f => r.findFirstIn(f.getName).isDefined)
    good ++ these.filter(_.isDirectory).flatMap(recursiveListFiles(_, r))
  }

  /*
   *input html
   *output tr s
  * */



  /*input tr
  * output td s
  * */
  def getTd(ori: String): Array[String] = {
    val raw:String=ori.clone.asInstanceOf[String]
    val results:List[String]=List[String]()

    _
  }
}

class Fetcher (val path:String,val regex:Regex) {
  def getFiles: Array[File] = {
    Fetcher.recursiveListFiles(new File(path),regex)
  }
  val files=getFiles
}
