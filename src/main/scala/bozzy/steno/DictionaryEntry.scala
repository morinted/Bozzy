package bozzy.steno

import bozzy.controllers.MainDictionary

import scala.collection.mutable.ListBuffer
import scalafx.beans.property.{ObjectProperty, StringProperty}

/**
  * Created by ted on 2016-02-08.
  */
class DictionaryEntry(entryStroke: String,
                      entryTranslation: String,
                      val format: DictionaryFormat.Value,
                      val dictionary: StenoDictionary) {
  val translation = new Translation(entryTranslation, format)
  val stroke = new Stroke(entryStroke, format)

  // Readable columns.
  val translation_display = StringProperty(translation.raw)
  val stroke_display = StringProperty(stroke.raw)
  val word_count = ObjectProperty(translation.word_count)
  val chord_count = ObjectProperty(stroke.chord_count)
  val collision_count = ObjectProperty(0)
  val dictionary_name = StringProperty(dictionary.dictionaryName)
}

object DictionaryEntry {
  def filterDictionaryEntry(translation: String, stroke: String, dictionaryName: String, chordCount: String, wordCount: String) = {
    val noTranslation = translation == null || translation.isEmpty()
    val noStroke = stroke == null || stroke.isEmpty()
    val noDictionaryName = dictionaryName == null ||
      dictionaryName.isEmpty() ||
      dictionaryName.equals("Any")
    val intWordCount = try {
      wordCount.toInt
    } catch {
      case e: NumberFormatException => None
    }
    val intChordCount = try {
      chordCount.toInt
    } catch {
      case e: NumberFormatException => None
    }
    val noChordCount = chordCount == null || chordCount.isEmpty() || intChordCount == None
    val noWordCount = wordCount == null || wordCount.isEmpty() || intWordCount == None
    (entry: DictionaryEntry) => {
      (noTranslation && noStroke && noDictionaryName) ||
        (noTranslation || entry.translation.raw.toLowerCase().contains(translation.toLowerCase)) &&
          (noStroke || entry.stroke.raw.toLowerCase().contains(stroke.toLowerCase)) &&
          (noDictionaryName || entry.dictionary_name.value == dictionaryName) &&
          (noChordCount || entry.stroke.chord_count.equals(intChordCount)) &&
          (noWordCount || entry.translation.word_count.equals(intWordCount))
    }
  }
}