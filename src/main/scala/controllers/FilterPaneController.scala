package controllers

import java.util.function.Predicate
import javafx.{ event => jfxEvent
              , fxml => jfxFxml
              }

import javafx.collections.transformation.FilteredList

import scalafx.application.Platform
import javafx.scene.input.InputMethodEvent

import scalafx.scene.control.TextField

/**
  * Created by Ian on 2/10/2016.
  */
class FilterPaneController extends MainController {

  @jfxFxml.FXML
  private def onTranslationTextChange(event: jfxEvent.ActionEvent): Unit = {
    println("text has changed to")
    println(list.size())

//    filter

  }
}
