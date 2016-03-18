/**
  * Created by ian on 1/25/2016.
  */

package controllers


import java.util.function.Predicate
import javafx.collections.FXCollections
import javafx.collections.transformation.FilteredList
import javafx.{ fxml => jfxFxml
              , event => jfxEvent
              }

import steno.{DictionaryEntry, StenoDictionary, DictionaryFormat}

import scalafx.application.Platform


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
  val dictionary2 = new StenoDictionary("/dictionaries/testSecondDictionary.json", DictionaryFormat.JSON)
  val allEntries = FXCollections.observableArrayList[DictionaryEntry]()

  allEntries.addAll(dictionary.entries)
  allEntries.addAll(dictionary2.entries)

  val p = new Predicate[DictionaryEntry] {
    override def test(t: DictionaryEntry): Boolean = true
  }
  val filteredEntries = new FilteredList(allEntries, p)
}
