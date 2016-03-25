package controllers

import java.net.URL
import java.util.ResourceBundle
import java.util.function.Predicate
import javafx.collections.{FXCollections}
import javafx.fxml.{Initializable, FXML}
import javafx.{event => jfxEvent
, fxml => jfxFxml
}
import javafx.scene.control.{ TextField => jTxt, ChoiceBox => jChoiceBox }

import steno.{DictionaryEntry}

/**
 * Created by Ian on 2/10/2016.
 */
class FilterPaneController extends MainController with Initializable {

  // get values from screen
  @FXML private var translation: jTxt = _
  @FXML private var stroke: jTxt = _
  @FXML private var chordCount: jTxt = _
  @FXML private var dictionary_box: jChoiceBox[String] = _
  @FXML private var wordCount: jTxt = _
  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    dictionary_box.setItems(FXCollections.observableArrayList( null,
      MainDictionary.dictionary.dictionary_name, MainDictionary.dictionary2.dictionary_name
      ))
  }

  @jfxFxml.FXML
  //event handler to filter
  private def onTranslationTextChange(event: jfxEvent.ActionEvent): Unit = {
    val filterTest = DictionaryEntry.filterDictionaryEntry(translation.textProperty.getValue,
      stroke.textProperty.getValue, dictionary_box.getValue, chordCount.textProperty.getValue,
      wordCount.textProperty.getValue)

    val p = new Predicate[DictionaryEntry] {
      override def test(entry: DictionaryEntry) = filterTest(entry)
    }
    MainDictionary.filteredEntries.setPredicate(p)
  }
}
