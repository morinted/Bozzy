package bozzy.steno

/**
  * Created by ted on 2016-02-08.
  */
class Translation(val raw: String, val format: DictionaryFormat.Value) {
  val word_count: Int = (raw split ' ').length
}
