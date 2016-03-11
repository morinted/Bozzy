package controllers

import java.util.function.Predicate
import javafx.fxml.FXML
import javafx.{ event => jfxEvent
              , fxml => jfxFxml
              }
import javafx.scene.control.{ TextField => jTxt }

import steno.{DictionaryEntry}

/**
  * Created by Ian on 2/10/2016.
  */
class FilterPaneController extends MainController {
  @FXML private var translation: jTxt = _
  @FXML private var stroke: jTxt = _


  @jfxFxml.FXML
  private def onTranslationTextChange(event: jfxEvent.ActionEvent): Unit = {
    var newTranslation = translation.textProperty.getValue
    var newStroke = stroke.textProperty.getValue
    val p = new Predicate[DictionaryEntry] {
      override def test(t: DictionaryEntry): Boolean = {
        val noTranslation = newTranslation == null || newTranslation.isEmpty()
        val noStroke = newStroke == null || newStroke.isEmpty()
        if (noTranslation && noStroke) {
          return true
        } else {
          return (noTranslation || t.translation.raw.toLowerCase().contains(newTranslation.toLowerCase)) &&
            (noStroke || t.stroke.raw.toLowerCase().contains(newStroke.toLowerCase))
        }
      }
    }
    MainDictionary.dictionary.filteredEntries.setPredicate(p)
  }
}
