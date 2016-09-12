package bozzy.steno

import java.util.regex.Pattern

/**
  * Created by ted on 2016-02-08.
  */
class StenoLayout( val name: String
                 , val beginningKeys: String
                 , val centerKeys: Tuple2[String, String]
                 , val endingKeys: String
                 , val numberKey: String = ""
                 , val numberMapping: Map[String, String] = Map()
                 ) {
  val groupPattern = {
    val builder = new StringBuilder((beginningKeys.length +
      centerKeys._1.length + endingKeys.length) * 2 + 16)
    builder ++= "^("
    beginningKeys foreach(character => {
      builder ++= StenoLayout.characterToEscapedString(character)
      builder += '?'
    })
    builder ++= ")?("
    centerKeys._1 foreach(character => {
      builder ++= StenoLayout.characterToEscapedString(character)
      builder += '?'
    })
    builder ++= ")?("
    endingKeys foreach(character => {
      builder ++= StenoLayout.characterToEscapedString(character)
      builder += '?'
    })
    builder ++= ")?$"
    builder.toString.r
  }
}

object StenoLayout {
  val STANDARD_STENO_LAYOUT =
    new StenoLayout( "English Stenography"
                   , "#STKPWHR"
                   , ("AO-*EU", "-")
                   , "FRPBLGTSDZ"
                   , "#"
                   , Map(
                      "1" -> "S",
                      "2" -> "T",
                      "3" -> "P",
                      "4" -> "H",
                      "5" -> "A",
                      "0" -> "O",
                      "6" -> "F",
                      "7" -> "P",
                      "8" -> "L",
                      "9" -> "T"
                     )
                   )
  val numeric = """\d""".r

  /** Normalize a set of chords, separated by the '/' character.
    *
    * Parses a stroke; a set of chords; into a consistent format
    * and returns whether or not the stroke is valid syntax according
    * to the steno layout.
    */
  def normalizeStroke(stroke: String) = {
    val chords = (stroke split '/')
    if (stroke.charAt(0) == '/' || stroke.last == '/') {
      (stroke, false)
    } else {
      def assembleChords(remaining: Array[String], result: StringBuilder): (String, Boolean) = {
        val (chord, valid) = normalizeChord(remaining.head)
        if (!valid) {
          (stroke, valid)
        } else if (remaining.tail.size == 0) {
          result ++= chord
          (result.toString, valid)
        } else {
          result ++= s"$chord/"
          assembleChords(remaining.tail, result)
        }
      }
      assembleChords(chords, new StringBuilder(stroke.length))
    }
  }

  /** Normalize a single chord to a unified format, and validate it.
    *
    * Parses a single chord, then spits out an equivalent chord in a
    * consistent style, meaning that we can use it to compare
    * identical chords that are formatted differently. We also validate
    * the chord according to steno order and keys, and if the parsing
    * fails, we return the given chord and valid false.
    */
  def normalizeChord(chord: String) = {
    val result = new StringBuilder(chord.length * 2)
    var valid = true // Will only return "normalized steno" if valid.

    // Get right pattern if numeric or not.
    val (pattern, cleaned) =
      if (chord.contains(STANDARD_STENO_LAYOUT.numberKey) || // Contains #.
        numeric.findFirstIn(chord).isDefined) { // Contains number.
      val noHash = chord.replaceAllLiterally("#", "") // Get rid of #.
      result += '#' // Result will start with #.
      val withoutNumbers = noHash.map(key =>
            STANDARD_STENO_LAYOUT.numberMapping.getOrElse(key.toString, key)
          ).mkString
      // Return pattern, cleaned up chord.
      (STANDARD_STENO_LAYOUT.groupPattern, withoutNumbers.trim)
    } else {
      // Not as much to do for a regular chord.
      (STANDARD_STENO_LAYOUT.groupPattern, chord.trim)
    }
    try {
      // Split into the three sections. Will be empty string if not present.
      val pattern(left, center, right) = cleaned
      result ++= left
      if (center.isEmpty && left.isEmpty) {
        valid = false // Can only have left-hand or center without hyphen.
      } else if (center == "-") {
        // Ensure it's not just a hyphen...
        if (left.isEmpty && right.isEmpty) {
          valid = false
        } else if (!right.isEmpty) {
          // Leave necessary hyphen. Left doesn't need it.
          result += '-'
        }
        // Leading left-hand stroke doesn't need hyphen.
      } else if (center contains "-") {
        // Drop if explicit hyphen if implicit hyphen is available.
        val hyphenIndex = center indexOf '-'
        result ++= center.substring(0, hyphenIndex)
        if (center.size > hyphenIndex + 1) {
          result ++= center.substring(hyphenIndex + 1)
        }
      } else {
        // Result was previously implicit, keep it that way.
        result ++= center
      }
      // Add right hand.
      result ++= right
    } catch {
      // Occurs when characters aren't in steno order or don't match key set.
      case _: MatchError => valid = false
    }
    (if (valid) result.toString else chord, valid)
  }

  def characterToEscapedString(character: Char): String =
    character match { // Full list: \.[{(*+?^$|
      case '\\' => "\\\\"
      case '*' => "\\*"
      case '+' => "\\+"
      case '?' => "\\?"
      case '^' => "\\^"
      case '$' => "\\$"
      case '.' => "\\."
      case '|' => "\\|"
      case _ => character.toString
    }
}