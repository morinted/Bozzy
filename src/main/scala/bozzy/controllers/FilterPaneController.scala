package bozzy.controllers

import scalafx.event.ActionEvent
import scalafx.scene.control.{Label, TextField, ChoiceBox}
import scalafxml.core.macros.sfxml

import bozzy.steno.{StenoDictionary, DictionaryEntry}

/**
 * Created by Ian on 2/10/2016.
 */
@sfxml
class FilterPaneController (private val filterLabel: Label,
                            private val translation: TextField,
                            private val stroke: TextField,
                            private val chordCount: TextField,
                            private val dictionary_box: ChoiceBox[String],
                            private val wordCount: TextField) {

  dictionary_box.items = StenoDictionary.openDictionaryNames

  MainDictionary.filteredEntries.onChange((buffer, sequence) => {
    filterLabel.text = s"Filter (${MainDictionary.filteredEntries.size}/${MainDictionary.allEntries.size})"
  })

  def onTranslationTextChange(event: ActionEvent) = {
    val filterTest = DictionaryEntry.filterDictionaryEntry(
      translation.text.value,
      stroke.text.value,
      dictionary_box.value.value,
      chordCount.text.value,
      wordCount.text.value
    )
    MainDictionary.filteredEntries.predicate = filterTest

  }
}
