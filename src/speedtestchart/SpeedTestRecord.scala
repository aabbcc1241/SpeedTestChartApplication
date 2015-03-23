package speedtestchart

import java.security.Timestamp

import scala.util.parsing.combinator.Parsers.ParseResult
import scala.util.parsing.json.Parser


/**
 * Created by beenotung on 3/23/15.
 */
object SpeedTestRecord{
  def newInstance(code:String):SpeedTestRecord={
    new Parser
  }
}
class SpeedTestRecord (ip:String,download:Double,upload:Double,location:String,os:String,browser:Browser,date:Timestamp){

}
class Browser(name:String,version:String){
  def this(mixed:Array[String] )={
    this(mixed(0),mixed(1))
  }
  def this(mixed:String)={
    this(mixed.split(' '))
  }
}
