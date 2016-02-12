package controllers

import javafx.{ event => jfxEvent
              , fxml => jfxFxml
              }

import scalafx.application.Platform

/**
  * Created by Ian on 2/10/2016.
  */
class FilterPaneController {
  @jfxFxml.FXML
  private def onTranslationTextChange(event: jfxEvent.ActionEvent): Unit = {
    println("text has changed to" + event.toString)
  }
}
