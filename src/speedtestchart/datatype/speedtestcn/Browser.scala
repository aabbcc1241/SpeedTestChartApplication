package speedtestchart.datatype.speedtestcn

class Browser(name:String,version:String){
  def this(mixed:Array[String] )={
    this(mixed(0),mixed(1))
  }
  def this(mixed:String)={
    this(mixed.split(' '))
  }
}
