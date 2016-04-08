package bozzy.steno

import scala.io.Source
import scalafx.collections.ObservableBuffer

/**
  * Created by ted on 2016-02-08.
  */
class StenoDictionary(val filename: String, val format: DictionaryFormat.Value) {
  val entries = ObservableBuffer[DictionaryEntry]()
  val dictionaryName = filename.split('/').last

  if (format == DictionaryFormat.RTF) {

    try {
      // Ideally, we'd have UTF-8
      DictionaryFormat.parseRtfDictionary(Source.fromFile(filename).mkString)
        .foreach((entry: (String, String)) =>
          entries add new DictionaryEntry(entry._1, entry._2, DictionaryFormat.RTF, dictionaryName))
    } catch {
      case _: Exception => {
        // But most traditional steno dictionaries will be in ANSI
        entries removeAll entries
        DictionaryFormat.parseRtfDictionary(Source.fromFile(filename, "Cp1252").mkString)
          .foreach((entry: (String, String)) =>
            entries add new DictionaryEntry(entry._1, entry._2, DictionaryFormat.RTF, dictionaryName))
      }
    }
  } else if (format == DictionaryFormat.JSON) {
    val jsonDictionaryString = Source.fromFile(filename).mkString
    DictionaryFormat.parseJsonDictionary(jsonDictionaryString).foreach((entry: (String, String)) =>
      entries add new DictionaryEntry(entry._1, entry._2, DictionaryFormat.JSON, dictionaryName))
  }
}

object StenoDictionary {
  val openDictionaryNames = new ObservableBuffer[String]
  openDictionaryNames add "Any"
}