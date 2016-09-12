import bozzy.steno.StenoLayout
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by ted on 2016-04-15.
  */
class StrokeNormalizationTest extends FlatSpec with Matchers {
  "normalizeChord" should "parse numberic chords" in {
    val tests = Map(
      "#1234-6789" -> "#STPH-FPLT",
      "1234-6789" -> "#STPH-FPLT",
      "1234#-6789" -> "#STPH-FPLT",
      "#K-" -> "#K",
      "-6R7B" -> "#-FRPB",
      "50-R" -> "#AOR",
      "12K3W4R" -> "#STKPWHR",
      "K5#" -> "#KA",
      "12K3W4R50*EU6R7B8G9SDZ" -> "#STKPWHRAO*EUFRPBLGTSDZ",
      "#" -> "#"
    )
    tests foreach((chordPair: (String, String)) => {
      val (before, after) = chordPair
      StenoLayout.normalizeChord(before)._1 should equal (after)
    })
  }

  "normalizeChord" should "detect invalid strokes" in {
    val tests = List("TSKJ", "-", "321-")
    tests foreach(stroke =>
      StenoLayout.normalizeChord(stroke)._2 should be (false)
    )
  }

  "normalizeChord" should "parse regular chords" in {
    val tests = Map(
      "STKP-" -> "STKP",
      "-FRPB" -> "-FRPB",
      "STKPWHRAO*EUFRPBLGTSDZ" -> "STKPWHRAO*EUFRPBLGTSDZ",
      "*D" -> "*D",
      "WR" -> "WR",
      "WR-" -> "WR",
      "*" -> "*",
      "R-EU" -> "REU",
      "S-S" -> "S-S",
      "AOEU" -> "AOEU"
    )
    tests foreach((chordPair: (String, String)) => {
      val (before, after) = chordPair
      StenoLayout.normalizeChord(before)._1 should equal (after)
    })
  }

  "normalizeStroke" should "parse a multi-stroke entry" in {
    val tests = Map(
      "STK-/TPHO-T" -> "STK/TPHOT",
      "RAO-EUR/RAOEUR/R-R/AOEU/AO-EU" -> "RAOEUR/RAOEUR/R-R/AOEU/AOEU"
    )
    tests foreach ((strokePair: (String, String)) => {
      val (before, after) = strokePair
      StenoLayout.normalizeStroke(before)._1 should equal (after)
    })
  }

  "normalizeStroke" should "correctly report invalid strokes" in {
    val tests = List(
      "STK-/TPHO--T",
      "STK-//STK",
      "STK-/"
    )
    tests foreach (stroke => {
      val result = StenoLayout.normalizeStroke(stroke)
      result._1 should equal (stroke)
      result._2 should be (false)
    })
  }

}

