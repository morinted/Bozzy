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
