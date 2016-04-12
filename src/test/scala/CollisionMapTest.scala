import bozzy.steno.{DictionaryEntry, StenoDictionary, DictionaryFormat}
import bozzy.controllers._
import org.scalatest._

import scala.collection.mutable.ListBuffer

/**
  * Created by Ted on 12/04/2016.
  */
class CollisionMapTest extends FlatSpec with Matchers {
  "The collision map" should "load all entries in a dictionary correctly" in {
    def verifySize(size: Int) = {
      MainDictionary.collisionMap.foreach((entry: (String, ListBuffer[DictionaryEntry])) => {
        entry._2.size should equal (size)
      })
    }
    val absolutePath = getClass.getResource("/sampleJSONDictionary.json").getPath
    // Exploiting the fact that we can have the same dictionary twice, at the moment...
    MainDictionary addDictionary absolutePath
    verifySize(1)
    MainDictionary addDictionary absolutePath
    verifySize(2)
    MainDictionary removeDictionary absolutePath
    verifySize(1)
    MainDictionary removeDictionary absolutePath
    verifySize(0)
  }
}
