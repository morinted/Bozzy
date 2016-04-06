package bozzy.controllers

import javafx.collections.transformation.SortedList

import bozzy.steno.{DictionaryEntry}
import scalafx.collections.transformation.SortedBuffer
import scalafx.scene.control.{TableColumn, TableView}
import scalafxml.core.macros.sfxml

@sfxml
class DictionaryContentPaneController(private val table: TableView[DictionaryEntry],
                                      private val strokeCol: TableColumn[DictionaryEntry, String],
                                      private val translationCol: TableColumn[DictionaryEntry, String],
                                      private val wordCountCol: TableColumn[DictionaryEntry, Int],
                                      private val chordCountCol: TableColumn[DictionaryEntry, Int]) {

  strokeCol.cellValueFactory = {_.value.stroke_display}
  translationCol.cellValueFactory = {_.value.translation_display}
  wordCountCol.cellValueFactory = { _.value.word_count }
  chordCountCol.cellValueFactory = { _.value.chord_count }

  val sortedBuffer = new SortedBuffer[DictionaryEntry](MainDictionary.filteredEntries)
  sortedBuffer.comparator bind table.comparator
  table.items = sortedBuffer
}
