package speedtestchart


//import javafx.application.Application


import java.io.{InputStreamReader, BufferedReader, FileInputStream, File}

import speedtestchart.fetcher.Fetcher

import scala.swing.SimpleSwingApplication
import scala.util.Random
import scala.util.matching.Regex
import scalafx.application.JFXApp



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

private object SpeedTestChartScalaFxApplication extends JFXApp {
  val random = new Random(System.currentTimeMillis())

  import scalafx.application.JFXApp.PrimaryStage
  import scalafx.scene._
  import scalafx.scene.effect.BoxBlur
  import scalafx.scene.paint.Color
  import scalafx.scene.paint.Color._
  import scalafx.scene.shape.Circle

  stage = new PrimaryStage {
    title = "SpeedTest Chart ScalaFx Application"
    width = 800
    height = 600
    scene = new Scene {
      fill = Color.WHEAT
      content = for (i <- 0 until 50) yield new Circle {
        centerX = random.nextInt(800)
        centerY = random.nextInt(800)
        radius=150
        fill=color(random.nextDouble(),random.nextDouble(),random.nextDouble(),0.2)
        effect=new BoxBlur(10,10,3)
      }
    }
    //scene=new Scene{FXMLLoader.load(getClass.getResource("/speedtestchart/gui/MyView.fxml"))}
  }
}

import javafx.application.Application
private class SpeedTestChartJavaFxApplication extends Application with Runnable {
  import javafx.scene.Scene
  import javafx.scene.control.{Button, Label}
  import javafx.scene.layout.{FlowPane, StackPane}
  import javafx.stage.Stage

  override def start(stage: Stage): Unit = {
    println("loading FX stage")
    stage.setTitle("SpeedTest Chart JavaFxApplication")
    val root = new StackPane()
    val loading = new Label("loading")
    //root.getChildren.add(loading)
    val hk = new Button("Speedtest.cn (Mainland)")

    val mainland = new Button("Speedtest.com (Hong Kong)")
    root.getChildren.add(hk)
    root.getChildren.add(mainland)
    val panel = new FlowPane
    panel.getChildren.add(hk)
    panel.getChildren.add(mainland)
    stage.setScene(new Scene(panel, 600, 450))
    root.getChildren.remove(loading)
    stage.show()
  }



  override def run(): Unit = {
    println("opening gui window")
    Application.launch(classOf[SpeedTestChartJavaFxApplication])
  }
}

private class SpeedTestChartApplicationRunnable extends SimpleSwingApplication with Runnable {

  import scala.swing.BorderPanel.Position._
  import scala.swing._

  val pathDialog: PathDialog = new PathDialog
  val fileListTextField = new TextField


  var fetcher: Fetcher = _
  var path: String = _
  var regex: String = _

  def readFile(fetcher: Fetcher) :Unit = {
    fetcher.getFiles.foreach(f=> readFile(f) )
  }
  def readFile(file:File): Unit ={
    val in=new FileInputStream(file)
    if(in.available()<1)return
    /*val array=Array.fill[Byte](in.available())(0)
    in.read(array)
    array.foreach(b=>print(b.toChar))*/
    val reader=new BufferedReader(new InputStreamReader(in))
    val lines=reader.lines()

    println()
  }

  def init = {
    fetcher = new Fetcher(path, new Regex(regex))
    updateView
    val result=Dialog.showConfirmation(null,"start?","confirm",Dialog.Options.OkCancel,Dialog.Message.Question,null)
    if(result.eq(Dialog.Result.Ok)){
      readFile(fetcher);
    }
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
          pathDialog.open
        }
        contents += Button("Speedtest.com (Hong Kong)") {
          pathDialog.open
        }
      }) = Center
    }
    size = new Dimension(400, 300)
    location = new Point(100, 100)
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
