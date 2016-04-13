import bozzy.controllers.{MainDictionary}
import org.scalatest.{Matchers, FlatSpec}
import bozzy.steno.{StenoDictionary}
/**
  * Created by Sophie on 08/04/2016.
  */
class RemoveDictionaryTest extends FlatSpec with Matchers {
  val jsonDict = getClass.getResource("/sampleJSONDictionary.json").getPath
  val rtfDict = getClass.getResource("/sampleRTFDictionary.rtf").getPath
  "The removeDictionary function" should "remove a dictionary if found from the open dictionaries list" in {
    MainDictionary addDictionary jsonDict

    MainDictionary.openDictionaries.size should equal (1)
    MainDictionary.openDictionaryNames get 0 should equal (
      MainDictionary.openDictionaries.get(0).dictionaryName
    )
    MainDictionary.allEntries.size should equal (24)

    MainDictionary removeDictionary jsonDict

    MainDictionary.openDictionaries.size should equal (0)
    MainDictionary.allEntries.size should equal (0)
    MainDictionary.openDictionaryNames.size should equal (0)

  }
  "The removeDictionary function" should "not remove a dictionary if not found from the open dictionaries list" in {
    MainDictionary addDictionary jsonDict

    MainDictionary.openDictionaries.size should equal (1)
    MainDictionary.allEntries.size should equal (24)

    MainDictionary removeDictionary rtfDict
    
    MainDictionary.openDictionaries.size should equal (1)
    MainDictionary.allEntries.size should equal (24)
    MainDictionary.openDictionaryNames.size should equal (1)
    MainDictionary.dictionaryFilterChoices.size should equal (2)

    MainDictionary removeDictionary jsonDict
  }

}
