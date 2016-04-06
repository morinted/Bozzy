package bozzy.steno

/**
  * Created by ted on 2016-02-08.
  */
class Translation(entry: String, format: DictionaryFormat.Value) {
  val raw: String = if (format == DictionaryFormat.JSON) {
    val begin = entry.indexOf(": \"") + 3
    val end = entry.lastIndexOf("\"")
    if (entry != null && begin > 0 && end > begin) {
      entry.substring(begin, end)
    } else {
      ""
    }
  } else if (format == DictionaryFormat.RTF) {
    val stroke = """\{\\\*\\cxs ([^}]+)\}(.+?)(\{\\\*\\c.*)?""".r
    entry match {
      case stroke(rawStroke, rawTranslation) => rawTranslation
      case stroke(rawStroke, rawTranslation, extra) => rawTranslation
      case _ => ""
    }
  } else {
    ""
  }
  val word_count: Int = raw.split(' ').length
}
