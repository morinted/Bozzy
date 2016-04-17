import bozzy.steno.{DictionaryEntry, DictionaryFormat, StenoDictionary}
import org.scalatest._

import scala.io.Source

/**
  * Created by ted on 2016-02-08.
  */
class StenoInstantiationTest extends FlatSpec with Matchers {
  val RTFDictionary = Source.fromURL(getClass.getResource("/sampleRTFDictionary.rtf")).getLines()
  val JSONDictionary = Source.fromURL(getClass.getResource("/sampleJSONDictionary.json")).getLines()

  "rtfLineToEntry" should "load a simple RTF translation" in {
    val (stroke, translation) = DictionaryFormat.rtfLineToEntry("{\\*\\cxs KOPB/SREPBGS}convention")
    stroke.get should equal ("KOPB/SREPBGS")
    translation.get should equal ("convention")

    val (secondStroke, secondTranslation) = DictionaryFormat.rtfLineToEntry(
      "{\\*\\cxs THR-L}there will{\\*\\cxsvatdictentrydate\\yr2016\\mo1\\da6}"
    )
    secondStroke.get should equal ("THR-L")
    secondTranslation.get should equal ("there will{\\*\\cxsvatdictentrydate\\yr2016\\mo1\\da6}")
    }
  "parseJsonDictionary" should "load a simple JSON dictionary" in {
    val entries = DictionaryFormat.parseJsonDictionary(
      """{"KOPB/SREPBGS": "convention"}"""
    )
    entries.size should equal (1)
    val (stroke, translation) = entries.iterator.next()
    stroke should equal ("KOPB/SREPBGS")
    translation should equal ("convention")
  }

  "The StenoDictionary class" should "load a simple JSON dictionary" in {
    val absolutePath = getClass.getResource("/sampleJSONDictionary.json").getPath()
    val dictionary = new StenoDictionary(absolutePath, DictionaryFormat.JSON)
    dictionary.entries.size should equal (24)
  }

  "Entry matching" should "use normalized strokes" in {
    val absolutePath = getClass.getResource("/sampleJSONDictionary.json").getPath()
    val dictionary = new StenoDictionary(absolutePath, DictionaryFormat.JSON)
    // A- is equivalent to A.
    val entry = new DictionaryEntry("A-", "{a^}", DictionaryFormat.JSON, dictionary)
    val secondEntry = new DictionaryEntry("A", "{a^}", DictionaryFormat.JSON, dictionary)
    entry.matches(secondEntry) should be (true)
    // TODO: Test against matching across format translations, e.g.:
    // "{a^}" == "a\cxds "
  }
}
