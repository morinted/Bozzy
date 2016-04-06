package bozzy.steno

import scala.io.Source
import scalafx.collections.ObservableBuffer
import play.api.libs.json._

/**
  * Created by ted on 2016-02-08.
  */
class StenoDictionary(val filename: String, val format: DictionaryFormat.Value) {
  val entries = ObservableBuffer[DictionaryEntry]()
  val dictionary_name = filename.split('/').last

  if (format == DictionaryFormat.RTF) {
    Source.fromFile(filename, "utf-8").getLines
      .foreach((line: String) => {
        val entry = new DictionaryEntry(line, DictionaryFormat.RTF, dictionary_name)
        if (entry.stroke.raw.length > 0) {
          entries.add(entry)
        }
      })
  } else if (format == DictionaryFormat.JSON) {
    val jsonDictionary: JsValue = Json.parse(Source.fromFile(filename, "utf-8").mkString)
    jsonDictionary.as[JsObject].keys.foreach(stroke => {
      // TODO: Allow DictionaryEntry to be made from stroke/translation
      val translation: String = (jsonDictionary \ stroke) match {
        case JsDefined(v) => v.toString
        case undefined: JsUndefined => ""
      }
      val entry = new DictionaryEntry(s""""$stroke": $translation,""", DictionaryFormat.JSON, dictionary_name)
      entries.add(entry)
    })
  }
  StenoDictionary.openDictionaryNames add dictionary_name
}

object StenoDictionary {
  val openDictionaryNames = new ObservableBuffer[String]
  openDictionaryNames add "Any"
}