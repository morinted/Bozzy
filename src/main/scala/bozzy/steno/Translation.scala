package bozzy.steno

/**
  * Created by ted on 2016-02-08.
  */
abstract class Translation(val raw: String, val format: DictionaryFormat.Value) {
  val word_count: Int = (raw split ' ').length
  def toJSON: String
  def toRTF: String
}

class RTFTranslation(override val raw: String) extends Translation(raw, DictionaryFormat.RTF) {
  def toRTF = raw
  def toJSON = raw // TODO: Implement conversion.
}

class JSONTranslation(override val raw: String) extends Translation(raw, DictionaryFormat.RTF) {
  def toRTF = raw // TODO: Implement conversion.
  def toJSON = raw
}

object Translation {
  val rtfMetadata = """\\\*\\cx([^ \\]+)(.*)""".r
  def rtfToJson(rtfTranslation: String): String = {
    val result = new StringBuilder
    def processPartial(rtfString: String) = {
      val brace = rtfString.indexOf('{')
      val backslash = rtfString.indexOf('\\')
      println(s"Back: $backslash Brace: $brace)")
      if (brace == -1 && backslash == -1) {
        result ++= rtfString
        result
      } else if (brace != -1 && (backslash == -1 || brace < backslash)) {
        val closeBrace = rtfString.indexOf('}')
        if (closeBrace > brace) {
          val contents = rtfString.substring(brace + 1, closeBrace)
          // Punctuation or metadata.
          println(s"$rtfTranslation: $contents")
          if (contents.startsWith("\\cxp")) {
            val punctuation = contents.substring(4).trim
            punctuation match {
              case "." => result ++= "{.}"
              case "?" => result ++= "{?}"
              case "!" => result ++= "{!}"
              case ":" => result ++= "{:}{-|}"
              case "," => result ++= "{,}"
              case ";" => result ++= "{;}"
              case "'" => result ++= "{^'}"
              case "/" => result ++= "{^/^}"
              case "-" => result ++= "{^-^}"
              case "\"" => result ++= "\"" // TODO: handle impossible conversion
              case other => result ++= s"{^$other^}"
            }
          } else if (contents.startsWith("\\*\\cx")) {
            // Arbitrary metadata
            val rtfMetadata(key, value) = contents
            println(s"--Key: $key \n--Value: $value")
            if (key == "cxsvatdictflags" && value == "N") {
              result ++= "{-|}"
            }
            // Don't add metadata to JSON
          }
        }
      } else {
        val nextSpace = rtfString.substring(backslash)
          .indexOf(' ')
        if (nextSpace != -1) {
          val contents = rtfString.substring(backslash + 1, nextSpace + backslash)
          println(s"$rtfTranslation: $contents")
        }
      }
    }
    processPartial(rtfTranslation)
    rtfTranslation.replaceAllLiterally("\\cxds ", "{^}")
  }
  def jsonToRtf(jsonTranslation: String): String = {
    jsonTranslation.replaceAllLiterally("{^}", "\\cxds")
  }
}