import bozzy.steno.{StenoDictionary, DictionaryEntry, DictionaryFormat}
import org.scalatest._

/**
  * Created by Sophie on 26/03/2016.
  */
class FilterTest extends FlatSpec with Matchers {

  val absolutePath = getClass.getResource("/sampleJSONDictionary.json").getPath
  val dictionary = new StenoDictionary(absolutePath, DictionaryFormat.JSON)
  val name = dictionary.dictionaryName
  /* Note:
  * filter(true) means keep entry
  * filter(false) means hide entry
  */

  "The filter function" should "keep entries if all queries are empty" in {
    val entry = new DictionaryEntry("KOPB/SREPBGS", "convention", DictionaryFormat.RTF, dictionary)
    var filter = DictionaryEntry.filterDictionaryEntry(null, null, null, null, null)
    filter(entry) should equal(true)
    filter = DictionaryEntry.filterDictionaryEntry("", "", "", "", "")
    filter(entry) should equal(true)
  }
  "The filter function" should "be case insensitive" in {
    val entry = new DictionaryEntry("KOPB/SREPBGS", "convention", DictionaryFormat.RTF, dictionary)
    val filter = DictionaryEntry.filterDictionaryEntry("CONVEN", "kopb", null, null, null)
    filter(entry) should equal(true)
  }
  "The filter function" should "keep entries that contain the query" in {
    val entry = new DictionaryEntry("KOPB/SREPBGS", "convention", DictionaryFormat.RTF, dictionary)
    val filter = DictionaryEntry.filterDictionaryEntry("tion", "SRE", name, "2", "1")
    filter(entry) should equal(true)
  }
  "The filter function" should "hide entries that do not contain the query" in {
    val entry = new DictionaryEntry("KOPB/SREPBGS", "convention", DictionaryFormat.RTF, dictionary)
    var filter = DictionaryEntry.filterDictionaryEntry("tion", "SRE", name, "2", "2")
    filter(entry) should equal(false)
    filter = DictionaryEntry.filterDictionaryEntry(" ", null, null, null, null)
    filter(entry) should equal(false)
  }
  "The filter function" should "keep entries if query does not make sense" in {
    val entry = new DictionaryEntry("KOPB/SREPBGS", "convention", DictionaryFormat.RTF, dictionary)
    var filter = DictionaryEntry.filterDictionaryEntry(null, null, name, "number", null)
    filter(entry) should equal(true)
    filter = DictionaryEntry.filterDictionaryEntry(null, null, name, null, "number")
    filter(entry) should equal(true)
  }
}
