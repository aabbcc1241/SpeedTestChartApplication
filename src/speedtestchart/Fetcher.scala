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
  def
}

class Fetcher (val path:String,val regex:Regex) {
  def getFiles: Array[File] = {
    Fetcher.recursiveListFiles(new File(path),regex)
  }
  val files=getFiles
}
