import bozzy.controllers.{MainDictionary}
import org.scalatest.{Matchers, FlatSpec}
import bozzy.steno.{StenoDictionary}
/**
  * Created by Sophie on 08/04/2016.
  */
class RemoveDictionaryTest extends FlatSpec with Matchers{

  "The removeDictionary function" should "remove a dictionary if found from the open dictionaries list" in {
    val path = "./src/main/resources/dictionaries/main.json"
    MainDictionary.addDictionary(path)
    MainDictionary.removeDictionary(path)
    MainDictionary.openDictionaries.size should equal(0)
    MainDictionary.allEntries.size should equal(0)
    StenoDictionary.openDictionaryNames.size should equal(1) //should still include option "any"
  }
  "The removeDictionary function" should "not remove a dictionary if not found from the open dictionaries list" in {
    val path = "./src/main/resources/dictionaries/testSecondDictionary.json"
    val pathOther = "./src/main/resources/dictionaries/testSecondDictionary.rtf"
    MainDictionary.addDictionary(path)
    MainDictionary.removeDictionary(pathOther)
    MainDictionary.openDictionaries.size should equal(1)
    MainDictionary.allEntries.size should equal(5)
    StenoDictionary.openDictionaryNames.size should equal(2) //should still include option "any"
  }

}
