import bozzy.controllers.MainDictionary
import bozzy.steno.{Translation, DictionaryEntry, DictionaryFormat, StenoDictionary}
import org.scalatest._

/**
  * Created by Sophie on 26/03/2016.
  */
class ConversionTest extends FlatSpec with Matchers {
  "rtfToJson" should "handle the attach/delete space command" in {
    val tests = Map(
      """\cxds , can't!""" -> "{^can}'t!", // verify spacing here, might be {^}can't!
      """\cxds -to-\cxds""" -> "{^-to-^}",
      """\cxds \cxds test""" -> "{^test}",
      """\cxds  \cxds""" -> "{^ ^}", // "{^ ^}" ?
      """prefix\cxds """ -> "{prefix^}",
      """extra prefix\cxds """ -> "{extra prefix^}",
      """\cxds suffix extra""" -> "{^suffix extra}",
      """\cxds ^""" -> "{^}^",
      """\cxds""" -> "{^}",
      """\cxds .com""" -> "{^}.com",
      """\cxds \cxds \cxds """ -> "{^}"
    )
    tests foreach (test => {
      val (before, after) = test
      Translation.rtfToJson(before)
    })
  }
  "rtfToJson" should "handle force lower case" in {
    val tests = Map(
      """\cxfl""" -> "{>}"
    )
    tests foreach (test => {
      val (before, after) = test
      Translation.rtfToJson(before)
    })
  }
  "rtfToJson" should "handle force cap" in {
    val tests = Map(
      """\cxfc""" -> "{-|}",
      """{\*\cxsvatdictflags N}""" -> "{-|}",
      """{\*\cxsvatdictflags LN1}""" -> "{-|}",
      """\par""" -> "{#Return Return}",
      """\s2""" -> "{#Return Return}",
      """\par\s1""" -> "{#Return Return}",
      """\par\s2""" -> "{#Return Return}{^    ^}"
    )
    tests foreach (test => {
      val (before, after) = test
      Translation.rtfToJson(before)
    })
  }
  "rtfToJson" should "unescape characters" in {
    val tests = Map(
      """\{#Return\}""" -> "{#Return}",
      """\\""" -> "\\",
      """\-""" -> "-"
    )
    tests foreach (test => {
      val (before, after) = test
      Translation.rtfToJson(before)
    })
  }
  "rtfToJson" should "handle fingerspelling" in {
    val tests = Map(
      """{\cxfing M}""" -> "{&M}",
      """{\cxstit M}""" -> "{&M-}" // warn
    )
    tests foreach (test => {
      val (before, after) = test
      Translation.rtfToJson(before)
    })
  }
  "rtfToJson" should "handle punctuation" in {
    val tests = Map(
      "." -> "{.}",
      ". " -> "{.} ",
      " . " -> " . ",
      "Mr." -> "Mr.",
      ".attribute" -> ".attribute",
      """\-""" -> "-",
      """\\""" -> """\\""",
      """\{""" -> "{",
      """\}""" -> "}",
      """\~""" -> "{^ ^}", // non-breaking space
      """\_""" -> "-",
      """\\n\\r""" -> "{#Return Return}",
      """\\r""" -> "{#Return Return}",
      """\\n""" -> "{#Return Return}",
      """{\cxp.}""" -> "{.}",
      """{\cxp ?}""" -> "{?}",
      """{\cxp! }""" -> "{!}",
      """{\cxp: }""" -> "{:}{-|}",
      """{\cxp   '   }""" -> "{^'}",
      """{\cxp- }""" -> "{^-^}",
      """{\cxp/ }""" -> "{^/^}",
      """{\cxp" }""" -> "\"", // No equivalent!
      """{\cxptesting }""" -> "{^testing^}",
      """\par\s1""" -> "{#Return Return}", // warn?
      """\par\s2""" -> "{#Return Return}", // warn?
      """\cxc""" -> "\\cxc", // warn about conflicts
      """\\r\\n""" -> "{#Return}",
      """*/*""" -> "{#Return Return}",
      """compar\cxds""" -> "{compar^}",
      """{\cxa Q.}.""" -> "Q..",
      """long    spacing""" -> "long{^    ^}spacing"
    )
    tests foreach (test => {
      val (before, after) = test
      Translation.rtfToJson(before)
    })
  }
  "rtfToJson" should "ignore conflicts" in {
    val tests = Map(
      """{\cxconf [{\cxc dorm}|{\cxc don't remember}]}""" ->
        """{\cxconf [{\cxc dorm}|{\cxc don't remember}]}"""
    )
    tests foreach (test => {
      val (before, after) = test
      Translation.rtfToJson(before)
    })
  }
  "rtfToJson" should "discard metadata" in {
    val tests = Map(
      """{\*\cxcomment Comment, hehe}""" -> "",
      """{\*\cswhatever asetaset}""" -> "",
      """{\*\cxsvatdictentrydate\yr2015\mo3\da3}""" -> ""
    )
    tests foreach (test => {
      val (before, after) = test
      Translation.rtfToJson(before)
    })
  }

  "jsonToRtf" should "handle the attach/delete space command" in {
    val tests = Map(
      "{^can't!}" -> """\cxds can't!""",
      "{^-to-^}" -> """\cxds -to-\cxds """,
      "{^test}" -> """\cxds \cxds test""",
      "{^} {^}" -> """\cxds  \cxds """, // \~ ?
      "{prefix^}" -> """prefix\cxds """,
      "{extra prefix^}" -> """extra prefix\cxds """,
      "{^suffix extra}" -> """\cxds suffix extra""",
      "{^}^" -> """\cxds ^""",
      "{^}.com" -> """\cxds .com""",
      "{^}{^}{^}{^^}" -> """\cxds """
    )
    tests foreach (test => {
      val (before, after) = test
      Translation.jsonToRtf(before)
    })
  }
}
