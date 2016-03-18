package controllers

import java.net.URL
import java.util.ResourceBundle
import javafx.collections.transformation.SortedList
import javafx.fxml.{Initializable, FXML}

import steno.{DictionaryEntry, DictionaryFormat}

import javafx.scene.control.{ TableView => jTable, TableColumn => jCol}
import scalafx.Includes._

class DictionaryContentPaneController extends MainController with Initializable {
  @FXML private var wordCountCol: jCol[DictionaryEntry, Int] = _
  @FXML private var chordCountCol: jCol[DictionaryEntry, Int] = _
  @FXML private var table: jTable[DictionaryEntry] = _
  @FXML private var strokeCol: jCol[DictionaryEntry, String] = _
  @FXML private var translationCol: jCol[DictionaryEntry, String] = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    strokeCol.cellValueFactory = {
      _.value.stroke_display
    }
    translationCol.cellValueFactory = {
      _.value.translation_display
    }
    wordCountCol.cellValueFactory = {
      _.value.word_count
    }
    chordCountCol.cellValueFactory = {
      _.value.chord_count
    }

    val sorted = new SortedList[DictionaryEntry](MainDictionary.filteredEntries)
    sorted.comparatorProperty.bind(table.comparatorProperty)
    table.setItems(sorted)
  }
}
