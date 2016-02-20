package controller

import java.net.URL
import java.util.ResourceBundle
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.{Initializable, FXML}

import scala.collection.mutable.ArrayBuffer
import scalafx.beans.property.StringProperty
import javafx.scene.control.{ TableView => jTable, TableColumn => jCol}
import scalafx.Includes._
import scalafx.collections.ObservableArray


class DictionaryEntry(stroke_ :String, translation_ :String) {
  val stroke = StringProperty(stroke_)
  val translation = StringProperty(translation_)
}

class DictionaryContentPaneController extends Initializable {
  @FXML private var table :jTable[DictionaryEntry] = _
  @FXML private var strokeCol :jCol[DictionaryEntry, String] = _
  @FXML private var translationCol :jCol[DictionaryEntry, String] = _


  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    strokeCol.cellValueFactory = { _.value.stroke }
    translationCol.cellValueFactory = { _.value.translation }

    val list :ObservableList[DictionaryEntry] = FXCollections.observableArrayList[DictionaryEntry]()
    (1 to 100000).foreach( (i :Int) => {
      list.add(new DictionaryEntry(s"stroke $i", s"translation $i"))
    })


    table.setItems(list)
  }
}
