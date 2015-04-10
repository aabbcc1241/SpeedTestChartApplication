package speedtestchart

import scala.swing.Label

//import javafx.application.Application


import java.io._

import speedtestchart.datatype.speedtest.cn.SpeedtestCnRecord
import speedtestchart.datatype.speedtest.com.SpeedtestComRecord
import speedtestchart.fetcher.Fetcher

import scala.collection.mutable.ArrayBuffer
import scala.swing.SimpleSwingApplication
import scala.util.Random
import scala.util.matching.Regex


/**
 * Created by beenotung on 3/23/15.
 */
object SpeedTestChartApplication extends Thread {
  private val runnable = new SpeedTestChartApplicationRunnable
  //private val runnable = new SpeedTestChartJavaFxApplication

  override def run = {
    //SpeedTestChartScalaFxApplication.main(Array.empty[String])
    runnable.run
  }
}


private class SpeedTestChartApplicationRunnable extends SimpleSwingApplication with Runnable {

  val mainland = "Mainland"
  val hk = "hk"
  val pathDialog: PathDialog = new PathDialog
  val fileDialog:SaveFileDialog=new SaveFileDialog

  import scala.swing.BorderPanel.Position._
  import scala.swing._

  val fileListTextField = new TextField
  var target: String = ""
  var fetcher: Fetcher = _
  var path: String = _
  var regex: String = _

  def init = {
    fetcher = new Fetcher(path, new Regex(regex))
    updateView
    val result = Dialog.showConfirmation(null, "start?", "confirm", Dialog.Options.OkCancel, Dialog.Message.Question, null)
    if (result.id == 0) {
      println("clicked ok")
      readFile(fetcher);
    }
  }

  def readFile(fetcher: Fetcher): Unit = {
    fetcher.getFiles.foreach(f => readFile(f))
  }

  def save(records: Array[SpeedtestComRecord]) = {
    println("save file")
    filename=""
    fileDialog.open
    while(filename=="")
    {
      println("waiting")
      Thread.sleep(500)
    }
    val out=new PrintWriter(filename)
    records.foreach(r=>{
      out.println()
      out.println(r.downloadSpeed)
      out.println(r.uploadSpeed)
    })
  }

  def readFile(file: File): Unit = {
    val in = new FileInputStream(file)
    if (in.available() < 1) return
    /*val array=Array.fill[Byte](in.available())(0)
    in.read(array)
    array.foreach(b=>print(b.toChar))*/
    val reader = new BufferedReader(new InputStreamReader(in))
    val lines = new ArrayBuffer[String]()
    do {
      lines += reader.readLine()
    } while (lines.last != null)
    if (target.equals(hk)) {
      println("loading hk data")
      val records = SpeedtestComRecord.decodeAll(lines.toArray)
      println(records.toVector.toString)
      save(records)
    }
    else if (target.equals(mainland)) {
      println("loading mainland data")
      SpeedtestCnRecord.decodeAll(lines.toArray)
    }
    else
      println("invalid target type (not hk nor mainland")
    println()
  }

  def updateView = {
    //TODO show all files from fetcher
    println("start files")
    fetcher.files.foreach(f => println(f.getPath))
    println("end files")
  }

  def setup = {
    println("list")
    for (file <- fetcher.files)
      println(file.toString)
  }

  override def run = {
    top.visible = true
    println("opening gui window")
    top.open
  }

  override def top: Frame = new MainFrame {
    title = "SpeedTest.cn Chart Application"
    contents = new BorderPanel {
      layout(new BoxPanel(Orientation.Vertical) {
        override val border = Swing.EmptyBorder(5, 5, 5, 5)
        contents += Button("Speedtest.cn (Mainland)") {
          target = mainland
          pathDialog.open
        }
        contents += Button("Speedtest.com (Hong Kong)") {
          target = hk
          pathDialog.open
        }
      }) = Center
    }
    size = new Dimension(400, 300)
    location = new Point(100, 100)
  }

  var filename = ""

  class SaveFileDialog extends Dialog {
    title = "Save File"
    modal = true
    val textField = new TextField

    contents = new BorderPanel {
      layout(new BoxPanel(Orientation.Vertical) {

        import scala.swing.Label

        override val border = Swing.EmptyBorder(5, 5, 5, 5)
        contents += new Label("File name:")
        contents += textField
      }) = Center

      layout(new FlowPanel(FlowPanel.Alignment.Right)(
        Button("OK") {
          filename = textField.text
          close()
        }
      )) = South
    }
  }

  class PathDialog extends Dialog {
    title = "Input Path"
    modal = true
    val pathTextField = new TextField
    val regexTextField = new TextField

    pathTextField.text = "/home/beenotung/files"
    regexTextField.text = """.*_Mar_.*\.html$"""

    contents = new BorderPanel {
      layout(new BoxPanel(Orientation.Vertical) {

        import scala.swing.Label

        override val border = Swing.EmptyBorder(5, 5, 5, 5)
        contents += new Label("Path:")
        contents += pathTextField
        contents += new Label("Regex:")
        contents += regexTextField
      }) = Center

      layout(new FlowPanel(FlowPanel.Alignment.Right)(
        Button("OK") {
          path = pathTextField.text
          regex = regexTextField.text
          close()
          init
          //setup
        }
      )) = South
    }

    override def open = {
      size = top.size
      location = top.location
      super.open
    }
  }

}
