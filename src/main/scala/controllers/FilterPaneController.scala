package controllers

import java.util.function.Predicate
import javafx.{ event => jfxEvent
              , fxml => jfxFxml
              }

import javafx.collections.transformation.FilteredList

import steno.{DictionaryFormat, DictionaryEntry}

import scalafx.application.Platform
import javafx.scene.input.InputMethodEvent

import scalafx.scene.control.TextField

/**
  * Created by Ian on 2/10/2016.
  */
class FilterPaneController extends MainController{

  var foo = false

  @jfxFxml.FXML
  private def onTranslationTextChange(event: jfxEvent.ActionEvent): Unit = {

    foo = !foo
    val p = new Predicate[DictionaryEntry] {
      override def test(t: DictionaryEntry): Boolean = foo
    }
    dictionary.filteredEntries.setPredicate(p)

    if(foo) {
      println("No entries are filtered")
    } else {
      println("All entries are filtered")
    }

    println(dictionary.filteredEntries.size())
  }
}
