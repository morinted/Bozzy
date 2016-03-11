/**
  * Created by ian on 1/25/2016.
  */

package controllers



import javafx.{ fxml => jfxFxml
              , event => jfxEvent
              }

import steno.{StenoDictionary}

import scalafx.application.Platform

import steno.DictionaryFormat


class MainController {

  @jfxFxml.FXML
  private def handleButtonPress(event: jfxEvent.ActionEvent): Unit = {
  }

  @jfxFxml.FXML
  private def handleExit(event: jfxEvent.ActionEvent): Unit = {
    Platform.exit()
  }

  @jfxFxml.FXML
  private def handleReadButton(event: jfxEvent.ActionEvent): Unit = {
  }
}

object MainDictionary {
  val dictionary = new StenoDictionary("/dictionaries/main.json", DictionaryFormat.JSON)
}
