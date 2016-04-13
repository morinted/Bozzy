package bozzy.steno

import java.io.File

import scala.io.Source
import scalafx.collections.ObservableBuffer

/**
  * Created by ted on 2016-02-08.
  */
class StenoDictionary(val filename: String, val format: DictionaryFormat.Value) {
  val entries = ObservableBuffer[DictionaryEntry]()
  val dictionaryName = new File(filename) getName

  if (format == DictionaryFormat.RTF) {

    try {
      // Ideally, we'd have UTF-8
      DictionaryFormat.parseRtfDictionary(Source.fromFile(filename).mkString)
        .foreach((entry: (String, String)) =>
          entries add new DictionaryEntry(entry._1, entry._2, DictionaryFormat.RTF, this))
    } catch {
      case _: Exception => {
        // But most traditional steno dictionaries will be in ANSI
        entries removeAll entries
        DictionaryFormat.parseRtfDictionary(Source.fromFile(filename, "Cp1252").mkString)
          .foreach((entry: (String, String)) =>
            entries add new DictionaryEntry(entry._1, entry._2, DictionaryFormat.RTF, this))
      }
    }
  } else if (format == DictionaryFormat.JSON) {
    val jsonDictionaryString = Source.fromFile(filename).mkString
    DictionaryFormat.parseJsonDictionary(jsonDictionaryString).foreach((entry: (String, String)) =>
      entries add new DictionaryEntry(entry._1, entry._2, DictionaryFormat.JSON, this))
  }
}

