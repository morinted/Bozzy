package bozzy.steno

import scalafx.beans.property.{BooleanProperty, ObjectProperty, StringProperty}

/**
  * Created by ted on 2016-02-08.
  */
class DictionaryEntry(entryStroke: String,
                      entryTranslation: String,
                      val format: DictionaryFormat.Value,
                      val dictionary: StenoDictionary) {
  val translation = format match {
    case DictionaryFormat.RTF => new RTFTranslation(entryTranslation)
    case DictionaryFormat.JSON => new JSONTranslation(entryTranslation)
  }
  val stroke = new Stroke(entryStroke, format)

  // Readable columns.
  val translation_display = StringProperty(translation.raw)
  val stroke_display = StringProperty(stroke.raw)
  val word_count = ObjectProperty(translation.word_count)
  val chord_count = ObjectProperty(stroke.chord_count)
  val collision_count = ObjectProperty(0)
  val dictionary_name = StringProperty(dictionary.dictionaryName)
  val is_duplicate = BooleanProperty(false)

  def matches(other: DictionaryEntry): Boolean =
    other.translation.raw == this.translation.raw &&
      other.stroke.normal == this.stroke.normal
}

object DictionaryEntry {
  def filterDictionaryEntry(translation: String, stroke: String, dictionaryName: String, chordCount: String,
                            wordCount: String, collisions: Boolean, hideDuplicates: Boolean) = {
    val noTranslation = translation == null || translation.isEmpty
    val noStroke = stroke == null || stroke.isEmpty
    val noDictionaryName = dictionaryName == null ||
      dictionaryName.isEmpty ||
      dictionaryName == "Any"
    val intWordCount = try {
      wordCount.toInt
    } catch {
      case _: NumberFormatException => None
    }
    val intChordCount = try {
      chordCount.toInt
    } catch {
      case _: NumberFormatException => None
    }
    val noChordCount = chordCount == null || chordCount.isEmpty || intChordCount == None
    val noWordCount = wordCount == null || wordCount.isEmpty || intWordCount == None
    (entry: DictionaryEntry) => {
      (noTranslation &&
        noStroke &&
        noDictionaryName &&
        noChordCount &&
        noWordCount &&
        !collisions &&
        !hideDuplicates) ||
        (noTranslation || entry.translation.raw.toLowerCase.contains(translation.toLowerCase)) &&
          (noStroke || entry.stroke.raw.toLowerCase.contains(stroke.toLowerCase)) &&
          (noDictionaryName || entry.dictionary_name.value == dictionaryName) &&
          (noChordCount || entry.stroke.chord_count == intChordCount) &&
          (noWordCount || entry.translation.word_count == intWordCount) &&
          (!collisions || entry.collision_count.value > 0) &&
          (!hideDuplicates || !entry.is_duplicate.value)
    }
  }
}