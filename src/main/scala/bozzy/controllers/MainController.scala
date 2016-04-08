/**
  * Created by ian on 1/25/2016.
  */

package bozzy.controllers

import scalafx.collections.ObservableBuffer
import scalafx.collections.transformation.FilteredBuffer
import scalafx.event.ActionEvent
import scalafx.stage.FileChooser
import scalafx.stage.FileChooser.ExtensionFilter
import scalafx.application.Platform
import scalafxml.core.macros.sfxml

import bozzy.steno.{DictionaryEntry, StenoDictionary, DictionaryFormat}

@sfxml
class MainController {

  def handleButtonPress(event: ActionEvent) {
  }

  def handleOpen(event: ActionEvent) {
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

  def handleExit(event: ActionEvent) {
    Platform.exit()
  }

  def handleReadButton(event: ActionEvent) {
  }
}

object MainDictionary {
  def addDictionary(path: String) {
    val lowerPath = path.toLowerCase()
    val newDictionary: StenoDictionary = if (lowerPath.endsWith("rtf")) {
      new StenoDictionary(path, DictionaryFormat.RTF)
    } else if (lowerPath.endsWith("json")) {
      new StenoDictionary(path, DictionaryFormat.JSON)
    } else {
      // TODO: Throw error
      null
    }
    if (newDictionary != null) {
      MainDictionary.openDictionaries add newDictionary
      StenoDictionary.openDictionaryNames add newDictionary.dictionaryName
      MainDictionary.allEntries addAll newDictionary.entries
    }
  }

  def removeDictionary(path: String){
    val selectedDictionary = path.split('/').last

    val foundDictionary = openDictionaries.find(dictionary =>
      dictionary.dictionaryName.equals(selectedDictionary))

    if (foundDictionary.isDefined) {
      MainDictionary.openDictionaries remove foundDictionary.get
      StenoDictionary.openDictionaryNames remove selectedDictionary
      MainDictionary.allEntries clear()
      openDictionaries.foreach(dictionary =>
        MainDictionary.allEntries addAll dictionary.entries)
    }
  }

  val openDictionaries = new ObservableBuffer[StenoDictionary]()
  val allEntries = new ObservableBuffer[DictionaryEntry]()
  val filteredEntries: FilteredBuffer[DictionaryEntry] = new FilteredBuffer[DictionaryEntry](allEntries)
}
