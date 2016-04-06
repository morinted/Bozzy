import bozzy.steno.{DictionaryEntry, DictionaryFormat}
import org.scalatest._

/**
  * Created by Sophie on 26/03/2016.
  */
class FilterTest extends FlatSpec with Matchers{

  /* Note:
  * filter(true) means keep entry
  * filter(false) means hide entry
  */

  "The filter function" should "keep entries if all queries are empty" in {
    var entry = new DictionaryEntry("{\\*\\cxs KOPB/SREPBGS}convention", DictionaryFormat.RTF, "main.rtf")
    var filter = DictionaryEntry.filterDictionaryEntry(null, null, null, null, null)
    filter(entry) should equal(true)
    filter = DictionaryEntry.filterDictionaryEntry("", "", "", "", "")
    filter(entry) should equal(true)
  }
  "The filter function" should "be case insensitive" in {
    var entry = new DictionaryEntry("{\\*\\cxs KOPB/SREPBGS}convention", DictionaryFormat.RTF, "main.rtf")
    var filter = DictionaryEntry.filterDictionaryEntry("CONVEN", "kopb", null, null, null)
    filter(entry) should equal(true)
  }
  "The filter function" should "keep entries that contain the query" in {
    var entry = new DictionaryEntry("{\\*\\cxs KOPB/SREPBGS}convention", DictionaryFormat.RTF, "main.rtf")
    var filter = DictionaryEntry.filterDictionaryEntry("tion", "SRE", "main.rtf", "2", "1")
    filter(entry) should equal(true)
  }
  "The filter function" should "hide entries that do not contain the query" in {
    var entry = new DictionaryEntry("{\\*\\cxs KOPB/SREPBGS}convention", DictionaryFormat.RTF, "main.rtf")
    var filter = DictionaryEntry.filterDictionaryEntry("tion", "SRE", "main.rtf", "2", "2")
    filter(entry) should equal(false)
    filter = DictionaryEntry.filterDictionaryEntry(" ", null, null, null, null)
    filter(entry) should equal(false)
  }
  "The filter function" should "keep entries if query does not make sense" in {
    var entry = new DictionaryEntry("{\\*\\cxs KOPB/SREPBGS}convention", DictionaryFormat.RTF, "main.rtf")
    var filter = DictionaryEntry.filterDictionaryEntry(null, null, "main.rtf", "number", null)
    filter(entry) should equal(true)
    filter = DictionaryEntry.filterDictionaryEntry(null, null, "main.rtf", null, "number")
    filter(entry) should equal(true)
  }
}
