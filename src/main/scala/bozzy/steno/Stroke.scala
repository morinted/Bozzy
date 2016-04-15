package bozzy.steno

/**
  * Created by ted on 2016-02-08.
  */
class Stroke(val raw: String, format: DictionaryFormat.Value)  {
  val (normal, valid) = StenoLayout.normalizeStroke(raw)
  val chord_count = (raw split '/') length
}
