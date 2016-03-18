package controllers

import java.util.function.Predicate
import javafx.fxml.FXML
import javafx.{event => jfxEvent
, fxml => jfxFxml
}
import javafx.scene.control.{TextField => jTxt}

import steno.{DictionaryEntry}

/**
 * Created by Ian on 2/10/2016.
 */
class FilterPaneController extends MainController {

  // get values from screen
  @FXML private var translation: jTxt = _
  @FXML private var stroke: jTxt = _
  @FXML private var chordCount: jTxt = _
  @FXML private var wordCount: jTxt = _

  @jfxFxml.FXML
  //event handler to filter
  private def onTranslationTextChange(event: jfxEvent.ActionEvent): Unit = {
    var newTranslation = translation.textProperty.getValue
    var newStroke = stroke.textProperty.getValue
    var newChordCount = chordCount.textProperty.getValue
    var newWordCount = wordCount.textProperty.getValue

    val p = new Predicate[DictionaryEntry] {

      override def test(t: DictionaryEntry): Boolean = {

        val noTranslation = newTranslation == null || newTranslation.isEmpty()
        val noStroke = newStroke == null || newStroke.isEmpty()
        val noCount = newChordCount == null || newChordCount.isEmpty()
        val noWordCount = newWordCount == null || newWordCount.isEmpty()

        if (noTranslation && noStroke && noCount && noWordCount) {
          return true
        } else {
          return (noTranslation || t.translation.raw.toLowerCase().contains(newTranslation.toLowerCase)) &&
            (noStroke || t.stroke.raw.toLowerCase().contains(newStroke.toLowerCase)) &&
            (noCount || t.stroke.chord_count.equals(newChordCount.toInt))&&
            (noWordCount || t.translation.word_count.equals(newWordCount.toInt))
        }
      }
    }
    MainDictionary.dictionary.filteredEntries.setPredicate(p)
  }
}
