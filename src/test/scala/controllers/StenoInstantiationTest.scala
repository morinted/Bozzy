package controllers

import java.security.InvalidParameterException

import org.scalatest._
import steno.{DictionaryEntry, DictionaryFormat, StenoDictionary}

import scala.io.Source

/**
  * Created by ted on 2016-02-08.
  */
class StenoInstantiationTest extends FlatSpec with Matchers {
  val RTFDictionary = Source.fromURL(getClass.getResource("/sampleRTFDictionary.rtf")).getLines()
  val JSONDictionary = Source.fromURL(getClass.getResource("/sampleJSONDictionary.json")).getLines()

  "The Entry class" should "load a simple RTF translation" in {
    var entry = new DictionaryEntry("{\\*\\cxs KOPB/SREPBGS}convention", DictionaryFormat.RTF)
    entry.stroke.raw should equal ("KOPB/SREPBGS")
    entry.translation.raw should equal ("convention")
    entry = new DictionaryEntry(
      "{\\*\\cxs THR-L}there will{\\*\\cxsvatdictentrydate\\yr2016\\mo1\\da6}",
      DictionaryFormat.RTF
    )
    entry.stroke.raw should equal ("THR-L")
    entry.translation.raw should equal ("there will")
    }
  "The Entry class" should "load a simple JSON translation" in {
    var entry = new DictionaryEntry("\"KOPB/SREPBGS\": \"convention\",", DictionaryFormat.JSON)
    entry.stroke.raw should equal ("KOPB/SREPBGS")
    entry.translation.raw should equal ("convention")
    entry = new DictionaryEntry(
      "\"TRAOEU/SER/TOPS\": \"Triceratops\",",
      DictionaryFormat.JSON
    )
    entry.stroke.raw should equal ("TRAOEU/SER/TOPS")
    entry.translation.raw should equal ("Triceratops")
  }

  "The StenoDictionary class" should "load a simple JSON dictionary" in {
    val dictionary = new StenoDictionary("/sampleJSONDictionary.json", DictionaryFormat.JSON)
    dictionary.entries.size should equal (24)
  }

}
