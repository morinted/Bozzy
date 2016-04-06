package bozzy.steno

/**
  * Created by ted on 2016-02-08.
  */
class Stroke(entry: String, format: DictionaryFormat.Value)  {
  val raw: String = if (format == DictionaryFormat.JSON) {
    val stroke = """"([^"]+)": "(.*)",?\w*$""".r
    entry match {
      case stroke(rawStroke, rawTranslation) => rawStroke
      case _ => ""
    }
  } else if (format == DictionaryFormat.RTF) {
    val stroke = """\{\\\*\\cxs ([^}]+)\}([^{]+).*$""".r
    entry match {
      case stroke(rawStroke, rawTranslation) => rawStroke
      case _ => ""
    }
  } else {
    ""
  }
  val chord_count :Int = raw.split('/').length
}
