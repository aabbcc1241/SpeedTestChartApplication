package speedtestchart

import scala.swing.BorderPanel.Position._
import scala.swing._
import scala.util.matching.Regex

/**
 * Created by beenotung on 3/23/15.
 */
class SpeedTestChartApplication extends SimpleSwingApplication {
  val pathDialog: PathDialog = new PathDialog
  val ui = new BorderPanel {
  }

  var fetcher: Fetcher = _
  var path: String = _
  var regex: String = _

  def init = {
    fetcher = new Fetcher(path, new Regex(regex))
  }
  def setup={
    println("list")
    for(file<-fetcher.files)
      println(file.toString)
  }

  def start = {
    top.visible = true
    //pathDialog.open()
    top.contents
    pathDialog.contents
  }

  override def top: Frame = new MainFrame {
    title = "SpeedTest.cn Chart Application"
    contents = ui
    size = new Dimension(800, 600)
    location = new Point(10, 10)
  }

  class PathDialog extends Dialog {
    title = "Input Path"
    modal = true
    val pathTextField = new TextField
    val regexTextField = new TextField

    pathTextField.text="/home/beenotung/files"
    regexTextField.text=""".*_Mar_.*\.html$"""

    contents = new BorderPanel {
      layout(new BoxPanel(Orientation.Vertical) {
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
          setup
        }
      )) = South
    }

    override def open={
      //size = new Dimension(800, 600)
      //location = new Point(10, 10)
      size = top.size.asInstanceOf[Dimension]
      location = top.location.asInstanceOf[Point]
      super.open
    }
  }
}
