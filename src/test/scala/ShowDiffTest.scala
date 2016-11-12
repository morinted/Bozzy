/**
 * Created by Erica on 2016-09-14.
 */
import bozzy.steno.{DictionaryEntry, DictionaryFormat, StenoDictionary}
import org.scalatest._
import scala.io.Source
import scalafx.collections.ObservableBuffer

class ShowDiffTest extends FlatSpec with Matchers{

    val RTFDictionary = Source.fromURL(getClass.getResource("/sampleRTFDictionary.rtf")).getLines()
    val JSONDictionary = Source.fromURL(getClass.getResource("/sampleJSONDictionary.json")).getLines()
    val absolutePath = getClass.getResource("/sampleJSONDictionary.json").getPath
    val dictionary = new StenoDictionary(absolutePath, DictionaryFormat.JSON)
    val dictionaryAfter = new StenoDictionary(getClass.getResource("/sampleJSONDictionaryCopy2.json").getPath, DictionaryFormat.JSON)
    val name = dictionary.dictionaryName

    "Show entries that were removed " should "show the removals in Dictionary 2 compared to Dictionary 1" in {

      val deleted = ObservableBuffer[DictionaryEntry]()
      val deletedEntry = ObservableBuffer[DictionaryEntry]()
      var a = null
      var b = null

      for (b <- dictionary.entries) {
        if(b.stroke.raw == "TPEFBG"){
          deletedEntry add b
        }
      }

      var removed = true;
      // for loop execution with a range
      for (b <- dictionary.entries) {
        removed = true;
        for (a <- dictionaryAfter.entries) {
          if (b.stroke.raw==a.stroke.raw) {
            removed = false;
          }
        }
        if (removed) {
          println(b.stroke.normal)
          deleted add b
        }
      }
      deleted should equal(deletedEntry)
    }

  "Show differences entries " should " point out the entries with different translations and " in {

    val modifieds = ObservableBuffer[DictionaryEntry]()
    val modified = ObservableBuffer[DictionaryEntry]()
    var b = null;
    for (b <- dictionary.entries) {
      if(b.stroke.raw == "SH-FT"){
        modified add b
      }
    }

    var a = null
    b = null
    var removed = true;
    // for loop execution with a range
    for (b <- dictionary.entries) {
      removed = true;
      for (a <- dictionaryAfter.entries) {
        if (b.stroke.raw==a.stroke.raw && b.translation.raw!=a.translation.raw) {
          println("Found")
          removed = false;
        }
      }
      if (!removed) {
        modifieds add b
      }
    }
    modifieds should equal(modified)
  }

  "Show entries that were add " should "show the added entries in Dictionary 2 compared to Dictionary 1" in {

    val added = ObservableBuffer[DictionaryEntry]()
    val addedEntry = ObservableBuffer[DictionaryEntry]()
    var b = null;
    for (b <- dictionaryAfter.entries) {
      if(b.stroke.raw == "R*RPlus"){
        addedEntry add b
      }
    }

    var a = null
    b = null
    var removed = true;
    // for loop execution with a range
    for (b <- dictionaryAfter.entries) {
      removed = true;
      for (a <- dictionary.entries) {
        if (b.stroke.raw==a.stroke.raw) {
          removed = false;
        }
      }
      if (removed) {
        println(b.stroke.normal)
        added add b
      }
    }
    added should equal(addedEntry)
  }

}
