package bozzy

import java.io.FileNotFoundException

import bozzy.controllers.MainDictionary

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.input.{TransferMode, DragEvent}
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
      onDragOver = (event: DragEvent) => {
        if (event.dragboard.hasFiles) {
          event acceptTransferModes TransferMode.COPY
        }
        event.consume
      }
      onDragDropped = (event: DragEvent) => {
        if (event.dragboard.hasFiles) {
          event.dragboard.files foreach (file => {
            file.getAbsolutePath.split('.').last.toLowerCase match {
              case "rtf" | "json" => {
                MainDictionary.addDictionary(file.getAbsolutePath)
                event.dropCompleted = true
              }
              case _ => event.dropCompleted = false
            }
          })
        }
        event.consume
      }
    }
  }
}
