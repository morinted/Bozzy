import java.io.FileNotFoundException
import javafx.{ fxml => jfxFxml
              , scene => jfxScene
              }

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._

object Main extends JFXApp{

  val root : jfxScene.Parent = jfxFxml.FXMLLoader.load(getClass.getResource("/view/Main.fxml"))

  if (root == null) throw new FileNotFoundException("couldn't find /fxml/Main.fxml")

  stage = new PrimaryStage() {
    title = "Bozzy"
    scene = new Scene(root)
  }
}
