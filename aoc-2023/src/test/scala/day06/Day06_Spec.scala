package day06

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers.contain
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import util.InputReader.readInput

class Day06_Spec extends AnyFlatSpec {
  private val day = "day06"
  private val lines = readInput(s"$day/data/input")


  "Puzzle One" should "return correct result" in {
    lines.map(l => One.run(Seq(l))) should contain theSameElementsInOrderAs
      List(7, 5, 6, 10, 11)
  }

  "Puzzle Two" should "return correct result" in {
    lines.map(l => Two.run(Seq(l))) should contain theSameElementsInOrderAs
      List(19, 23, 23, 29, 26)
  }
}
