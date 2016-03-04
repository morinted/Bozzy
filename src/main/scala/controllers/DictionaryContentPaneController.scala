package controllers

import java.net.URL
import java.util.ResourceBundle
import javafx.collections.ListChangeListener.Change
import javafx.collections.{ListChangeListener, FXCollections, ObservableList}
import javafx.{fxml, event}
import javafx.fxml.{Initializable, FXML}

import scala.collection.mutable.ArrayBuffer
import scalafx.beans.property.StringProperty
import javafx.scene.control.{ TableView => jTable, TableColumn => jCol}
import scalafx.Includes._
import scalafx.collections.ObservableArray
import java.util.function.Predicate
import javafx.collections.transformation.FilteredList


class DictionaryEntry(stroke_ :String, translation_ :String) {
  val stroke = StringProperty(stroke_)
  val translation = StringProperty(translation_)
}

class DictionaryContentPaneController extends MainController with Initializable {

  @FXML var table :jTable[DictionaryEntry] = _
  @FXML var strokeCol :jCol[DictionaryEntry, String] = _
  @FXML var translationCol :jCol[DictionaryEntry, String] = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    strokeCol.cellValueFactory = { _.value.stroke }
    translationCol.cellValueFactory = { _.value.translation }

    (1 to 10).foreach( (i :Int) => {
            list.add(new DictionaryEntry(s"stroke $i", s"translation $i"))
          })

    table.setItems(list)
  }

  def add(): Unit = {
    list.add(new DictionaryEntry("stroke a", "translation a"))

  }
}