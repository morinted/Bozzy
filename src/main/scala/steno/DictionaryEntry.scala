package steno

/**
  * Created by ted on 2016-02-08.
  */
class DictionaryEntry(val entry: String, val format: DictionaryFormat.Value) {
  val raw = entry
  val translation = new Translation(entry, format)
  val stroke = new Stroke(entry, format)
}
