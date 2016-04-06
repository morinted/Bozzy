package bozzy.steno

/**
  * Created by ted on 2016-02-08.
  */
class StenoLayout( val name: String
                 , val beginningKeys: String
                 , val centerKeys: Tuple2[String, String]
                 , val endingKeys: String
                 )

object StenoLayout {
  val STANDARD_STENO_LAYOUT =
    new StenoLayout( "English Stenography"
                   , "STKPWHR"
                   , ("AO*EU", "-")
                   , "FRPBLGTSZD"
                   )
  val STANDARD_NUMBER_LAYOUT =
    new StenoLayout( "English Stenography Number Bar"
                   , "12K3W4R"
                   , ("50*EU", "-")
                   , "6R7B8G9SDZ"
                   )
}