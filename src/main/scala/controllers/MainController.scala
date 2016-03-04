/**
  * Created by ian on 1/25/2016.
  */

package controllers



import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.{Initializable, FXML}
import javafx.scene.control.{ TableView => jTable, TableColumn => jCol}
import javafx.{ fxml => jfxFxml
              , event => jfxEvent
              }

import steno.{DictionaryFormat, DictionaryEntry, StenoDictionary}

import scalafx.application.Platform

import steno.DictionaryFormat


class MainController {

  var dictionary = new StenoDictionary("/dictionaries/main.json", DictionaryFormat.JSON)

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


