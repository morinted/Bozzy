package bozzy.steno

/**
  * Created by ted on 2016-02-08.
  */
abstract class Translation(val raw: String, val format: DictionaryFormat.Value) {
  val word_count: Int = (raw split ' ').length
  def toJSON: String
  def toRTF: String
}

class RTFTranslation(override val raw: String) extends Translation(raw, DictionaryFormat.RTF) {
  def toRTF = raw
  def toJSON = raw // TODO: Implement conversion.
}

class JSONTranslation(override val raw: String) extends Translation(raw, DictionaryFormat.RTF) {
  def toRTF = raw // TODO: Implement conversion.
  def toJSON = raw
}
