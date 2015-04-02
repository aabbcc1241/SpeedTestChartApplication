package speedtestchart.fetcher

/**
 * Created by beenotung on 3/23/15.
 */

import java.io.File

import scala.util.matching.Regex

object Fetcher {
  def recursiveListFiles(directory: File, r: Regex): Array[File] = {
    try{
    val files = directory.listFiles
    val result = files.filter(f => r.findFirstIn(f.getName).isDefined)
    result ++ files.filter(_.isDirectory).flatMap(recursiveListFiles(_, r))
    }catch {
      case e:NullPointerException=>Array.empty[File]
    }
  }

  /*
   *input html
   *output tr s
  * */


  /*input tr
  * output td s
  * */
  def getTd(ori: String): Array[String] = {
    val raw: String = ori + ""
    val results: List[String] = List[String]()
    //TODO
    null
  }
}

class Fetcher(val path: String, val regex: Regex) {
  def getFiles: Array[File] = {
    Fetcher.recursiveListFiles(new File(path), regex)
  }

  val files = getFiles
}
