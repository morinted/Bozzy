package bozzy.steno

/**
  * Created by ted on 2016-02-08.
  */
class Stroke(val raw: String, format: DictionaryFormat.Value)  {
  val chord_count: Int = (raw split '/').length
}
