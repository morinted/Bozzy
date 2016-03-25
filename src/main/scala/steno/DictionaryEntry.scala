package steno

import scalafx.beans.property.{ObjectProperty, StringProperty}

/**
  * Created by ted on 2016-02-08.
  */
class DictionaryEntry(val entry: String, val format: DictionaryFormat.Value, val name: String) {
  val raw = entry
  val translation = new Translation(entry, format)
  val stroke = new Stroke(entry, format)
  val dictionary_name = name

  // Readable columns.
  val translation_display = StringProperty(translation.raw)
  val stroke_display = StringProperty(stroke.raw)
  val word_count = ObjectProperty(translation.word_count)
  val chord_count = ObjectProperty(stroke.chord_count)
}

object DictionaryEntry {
  def filterDictionaryEntry(translation: String, stroke: String, dictionaryName: String, chordCount: String, wordCount: String) = {
    val noTranslation = translation == null || translation.isEmpty()
    val noStroke = stroke == null || stroke.isEmpty()
    val noDictionaryName = dictionaryName == null || dictionaryName.isEmpty()

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
          (noDictionaryName || entry.dictionary_name == dictionaryName) &&
          (noChordCount || entry.stroke.chord_count.equals(intChordCount)) &&
          (noWordCount || entry.translation.word_count.equals(intWordCount))
    }
  }
}