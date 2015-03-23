package speedtestchart

import java.security.Timestamp

/**
 * Created by beenotung on 3/23/15.
 */
class SpeedTestRecord (ip:String,download:Double,upload:Double,location:String,os:String,browser:Browser,date:Timestamp){

}
class Browser(name:String,version:String){
  def this(mixed:String)={
    this(mixed.split(' '))
  }
}
