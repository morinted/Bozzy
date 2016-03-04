package steno

import java.security.InvalidParameterException
import javafx.collections.{FXCollections, ObservableList}

import steno.DictionaryFormat

import scala.io.Source

/**
  * Created by ted on 2016-02-08.
  */
class StenoDictionary(val filename :String, val format :DictionaryFormat.Value) {
  val entries :ObservableList[DictionaryEntry] = FXCollections.observableArrayList[DictionaryEntry]()
  val JSONDictionary = Source.fromURL(getClass.getResource(filename)).getLines()
  JSONDictionary.foreach( (line :String) => {
    val entry = new DictionaryEntry(line, DictionaryFormat.JSON)
    if (entry.stroke.raw.length > 0) {
      entries.add(entry)
    }
  })
}
