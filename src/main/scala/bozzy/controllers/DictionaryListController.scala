package bozzy.controllers

/**
  * Created by Ian on 4/8/2016.
  */


import scalafx.event.ActionEvent
import scalafx.scene.control.ListView
import scalafx.stage.FileChooser
import scalafx.stage.FileChooser.ExtensionFilter
import scalafxml.core.macros.sfxml

import bozzy.steno.StenoDictionary

@sfxml
class DictionaryListController (private val dictionary_list: ListView[StenoDictionary]) {

  dictionary_list.items = MainDictionary.openDictionaries
  def handleAdd (event: ActionEvent) {
    val fileChooser = new FileChooser {
      title = "Open Resource File"
      extensionFilters.add(
        new ExtensionFilter("Steno Dictionaries", Seq("*.json", "*.rtf"))
      )
    }
    val selectedFile = fileChooser.showOpenDialog(bozzy.Bozzy.stage)
    if (selectedFile != null) {
      MainDictionary.addDictionary(selectedFile.getAbsolutePath)
    }
  }
  def handleRemove (event: ActionEvent) = {
    val selectedDictionary = dictionary_list.selectionModel.value.getSelectedItem

    if (selectedDictionary != null) {
      MainDictionary.removeDictionary(selectedDictionary.filename)
    }
  }
  def handleMoveUp (event: ActionEvent) = {}
  def handleMoveDown (event: ActionEvent) = {}
}
