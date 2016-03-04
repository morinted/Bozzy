/**
  * Created by ian on 1/25/2016.
  */

package controllers


import java.net.URL
import java.util.ResourceBundle
import java.util.function.Predicate
import javafx.collections.transformation.FilteredList
import javafx.collections.{FXCollections, ObservableList}
import javafx.collections.ListChangeListener
import javafx.collections.ListChangeListener.Change
import javafx.fxml.{Initializable, FXML}
import javafx.scene.control.{ TableView => jTable, TableColumn => jCol}
import javafx.{ fxml => jfxFxml
              , event => jfxEvent
              }

import steno.{DictionaryEntry, StenoDictionary}

import scalafx.application.Platform


class MainController extends Initializable{

  @FXML var list :ObservableList[DictionaryEntry] =_

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    list = FXCollections.observableArrayList[DictionaryEntry]()
  }

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


