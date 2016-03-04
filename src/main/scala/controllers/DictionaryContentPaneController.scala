package controllers

import java.net.URL
import java.util.ResourceBundle
import javafx.collections.ListChangeListener.Change
import javafx.collections.{ListChangeListener, FXCollections, ObservableList}
import javafx.{fxml, event}
import javafx.fxml.{Initializable, FXML}

import steno.{StenoDictionary, DictionaryFormat, DictionaryEntry}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scalafx.beans.property.StringProperty
import javafx.scene.control.{ TableView => jTable, TableColumn => jCol}
import scalafx.Includes._
import scalafx.collections.ObservableArray
import java.util.function.Predicate
import javafx.collections.transformation.FilteredList


class DictionaryContentPaneController extends MainController with Initializable {
  @FXML private var wordCountCol :jCol[DictionaryEntry, Int] = _
  @FXML private var chordCountCol :jCol[DictionaryEntry, Int] = _
  @FXML private var table :jTable[DictionaryEntry] = _
  @FXML private var strokeCol :jCol[DictionaryEntry, String] = _
  @FXML private var translationCol :jCol[DictionaryEntry, String] = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    strokeCol.cellValueFactory = { _.value.stroke_display }
    translationCol.cellValueFactory = { _.value.translation_display }
    wordCountCol.cellValueFactory = { _.value.word_count }
    chordCountCol.cellValueFactory = { _.value.chord_count }
    val dictionary = new StenoDictionary("/dictionaries/main.json", DictionaryFormat.JSON)
    table.setItems(dictionary.entries)
  }
}