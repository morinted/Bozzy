package bozzy

import java.io.FileNotFoundException

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.text.Font
import scalafxml.core.{NoDependencyResolver, FXMLView}

/**
  * Created by ted on 2016-04-06.
  */
object Bozzy extends JFXApp {
  Font.loadFont(
    getClass.getResource("/fonts/symbola_hint_8.ttf").toExternalForm,
    14
  )

  val resource = getClass.getResource("/view/Main.fxml")
  if (resource == null) {
    throw new FileNotFoundException("Cannot load resource: /view/Main.fxml")
  }
  val root = FXMLView(resource, NoDependencyResolver)

  stage = new PrimaryStage() {
    title = "Bozzy"
    scene = new Scene(root) {
      stylesheets = List(getClass.getResource("/css/style.css").toExternalForm)
    }

  }
}
