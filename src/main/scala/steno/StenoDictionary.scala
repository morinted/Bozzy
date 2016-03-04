package steno

import java.util.function.Predicate
import javafx.collections.transformation.FilteredList
import javafx.collections.{FXCollections, ObservableList}

import scala.io.Source

/**
  * Created by ted on 2016-02-08.
  */
class StenoDictionary(val filename :String, val format :DictionaryFormat.Value) {
  val entries = FXCollections.observableArrayList[DictionaryEntry]()
  val JSONDictionary = Source.fromURL(getClass.getResource(filename)).getLines()
  JSONDictionary.foreach( (line :String) => {
    val entry = new DictionaryEntry(line, DictionaryFormat.JSON)
    if (entry.stroke.raw.length > 0) {
      entries.add(entry)
    }
  })
  val p = new Predicate[DictionaryEntry] {
    override def test(t: DictionaryEntry): Boolean = true
  }
  val filteredEntries = new FilteredList(entries, p)
}
