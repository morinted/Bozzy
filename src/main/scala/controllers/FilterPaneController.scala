package controllers

import java.net.URL
import java.util.ResourceBundle
import java.util.function.Predicate
import javafx.collections.{FXCollections}
import javafx.fxml.{Initializable, FXML}
import javafx.{ event => jfxEvent
              , fxml => jfxFxml
              }
import javafx.scene.control.{ TextField => jTxt, ChoiceBox => jChoiceBox }

import steno.{DictionaryEntry}

/**
  * Created by Ian on 2/10/2016.
  */
class FilterPaneController extends MainController with Initializable {
  @FXML private var translation: jTxt = _
  @FXML private var stroke: jTxt = _
  @FXML private var dictionary_box: jChoiceBox[String] = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    dictionary_box.setItems(FXCollections.observableArrayList( null,
      MainDictionary.dictionary.dictionary_name, MainDictionary.dictionary2.dictionary_name
      ))
  }

  @jfxFxml.FXML
  private def onTranslationTextChange(event: jfxEvent.ActionEvent): Unit = {
    var newTranslation = translation.textProperty.getValue
    var newStroke = stroke.textProperty.getValue
    var newChoice = dictionary_box.getValue
    val p = new Predicate[DictionaryEntry] {
      override def test(t: DictionaryEntry): Boolean = {
        val noTranslation = newTranslation == null || newTranslation.isEmpty()
        val noStroke = newStroke == null || newStroke.isEmpty()
        val noChoice = newChoice == null || newChoice.isEmpty()
        if (noTranslation && noStroke && noChoice) {
          return true
        } else {
          return (noTranslation || t.translation.raw.toLowerCase().contains(newTranslation.toLowerCase)) &&
            (noStroke || t.stroke.raw.toLowerCase().contains(newStroke.toLowerCase)) &&
            (noChoice || t.dictionary_name == newChoice)
        }
      }
    }
    MainDictionary.filteredEntries.setPredicate(p)
  }
}
