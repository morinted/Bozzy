/**
  * Created by ian on 1/25/2016.
  */

package bozzy.controllers

import java.io.File

import scala.collection.mutable.ListBuffer
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
  val openDictionaryNames = new ObservableBuffer[String]
  val dictionaryFilterChoices = new ObservableBuffer[String]
  dictionaryFilterChoices add "Any"

  val openDictionaries = new ObservableBuffer[StenoDictionary]
  val allEntries = new ObservableBuffer[DictionaryEntry]
  val filteredEntries: FilteredBuffer[DictionaryEntry] = new FilteredBuffer[DictionaryEntry](allEntries)
  val collisionMap = collection.mutable.Map[String, ListBuffer[DictionaryEntry]]()

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
      MainDictionary.openDictionaryNames add newDictionary.dictionaryName
      MainDictionary.dictionaryFilterChoices add newDictionary.dictionaryName
      MainDictionary.allEntries addAll newDictionary.entries
      newDictionary.entries foreach (entry => {
        val buffer = collisionMap.getOrElseUpdate(entry.stroke.raw, ListBuffer[DictionaryEntry]())
        buffer += entry
        val size = buffer.size
        val duplicate = !buffer.exists(otherEntry => !entry.matches(otherEntry)) && size > 1
        buffer foreach (modifiedEntry => {
          modifiedEntry.collision_count() = size - 1
          modifiedEntry.is_duplicate() = duplicate
        })
      })
    }
  }

  def removeDictionary(path: String) {
    val foundDictionary = openDictionaries.find(dictionary => dictionary.filename == path)
    if (foundDictionary.isDefined) {
      val dictionary = foundDictionary.get
      MainDictionary.openDictionaries remove dictionary
      MainDictionary.openDictionaryNames remove dictionary.dictionaryName
      MainDictionary.dictionaryFilterChoices remove dictionary.dictionaryName
      dictionary.entries foreach (entry => {
        val maybeBuffer = collisionMap.get(entry.stroke.raw)
        if (maybeBuffer.isDefined) {
          val buffer = maybeBuffer.get -= entry
          val size = buffer.size
          val duplicate = !buffer.exists(otherEntry => !entry.matches(otherEntry)) && size > 1
          buffer foreach (modifiedEntry => {
            modifiedEntry.collision_count() = size - 1
            modifiedEntry.is_duplicate() = duplicate
          })
        }
      })
      MainDictionary.allEntries clear()
      openDictionaries.foreach(dictionary =>
        MainDictionary.allEntries addAll dictionary.entries)
    }
  }


}
