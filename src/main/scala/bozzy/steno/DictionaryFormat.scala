package bozzy.steno

import play.api.libs.json._

/**
  * Created by ted on 2016-02-08.
  */
object DictionaryFormat extends Enumeration {
  val RTF, JSON = Value
  def parseJsonDictionary(jsonDictionaryString: String) = {
    val jsonDictionary = Json.parse(jsonDictionaryString)
    jsonDictionary.as[JsObject].keys.map((stroke: String) => {
      val translation: String = jsonDictionary \ stroke match {
        case JsDefined(v) => v.as[JsString].value
        case _ => ""
      }
      (stroke, translation)
    })
  }
  def parseRtfDictionary(dictionary: String) = {
    dictionary.replaceAll("""\r?\n?""", "") // Ignore new lines
      .split("""\{\\\*\\cxs """) // Look at each stroke
      .drop(1) // Drop metadata
      .flatMap((line: String) => {
      val (stroke, translation) = DictionaryFormat.rtfLineToEntry(line, false)
      if (stroke.isDefined && translation.isDefined) {
        Option((stroke.get, translation.get))
      } else {
        None
      }
    })
  }
  def rtfLineToEntry(rtfLine: String, hasPrefix: Boolean = true): (Option[String], Option[String]) = {
    val strokeBegin = "{\\*\\cxs "
    val strokeEnd = "}"
    val strokeBeginIndex = if (hasPrefix) rtfLine.indexOf(strokeBegin) + strokeBegin.length else 0
    val strokeEndIndex = rtfLine.indexOf(strokeEnd, strokeBeginIndex)
    if (strokeBeginIndex >= 0 && strokeEndIndex > strokeBeginIndex) {
      (Some(rtfLine.substring(strokeBeginIndex, strokeEndIndex)),
        Some(rtfLine.substring(strokeEndIndex + 1))
      )
    } else {
      println(s"Trouble processing $rtfLine")
      (None, None)
    }
  }
}
