package bozzy.controllers

import scalafx.collections.ObservableBuffer
import scalafx.event.ActionEvent
import scalafx.scene.control.SpinnerValueFactory.ListSpinnerValueFactory
import scalafx.scene.control._
import scalafx.util.StringConverter
import scalafxml.core.macros.sfxml

import bozzy.steno.{StenoDictionary, DictionaryEntry}

/**
 * Created by Ian on 2/10/2016.
 */
@sfxml
class FilterPaneController (private val filterLabel: Label,
                            private val translation: TextField,
                            private val stroke: TextField,
                            private val chordCount: Spinner[String],
                            private val dictionary_box: ChoiceBox[String],
                            private val wordCount: Spinner[String],
                            private val collisions_checkbox: CheckBox) {

  dictionary_box.items = StenoDictionary.openDictionaryNames

  val choicesWithZero = ObservableBuffer[String]("", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
  val choicesWithoutZero = ObservableBuffer[String]("", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
  chordCount.setValueFactory(new ListSpinnerValueFactory[String](choicesWithoutZero))
  wordCount.setValueFactory(new ListSpinnerValueFactory[String](choicesWithZero))

  MainDictionary.filteredEntries.onChange((buffer, sequence) => {
    filterLabel.text = s"Filter (${MainDictionary.filteredEntries.size}/${MainDictionary.allEntries.size})"
  })

  def onTranslationTextChange(event: ActionEvent) = {
    val chordCountString = if (choicesWithoutZero.contains(chordCount.value.value)) chordCount.value.value else ""
    val wordCountString = if (choicesWithZero.contains(wordCount.value.value)) wordCount.value.value else ""
    val filterTest = DictionaryEntry.filterDictionaryEntry(
      translation.text.value,
      stroke.text.value,
      dictionary_box.value.value,
      chordCountString,
      wordCountString,
      collisions_checkbox.selected.value
    )
    MainDictionary.filteredEntries.predicate = filterTest

  }
}
