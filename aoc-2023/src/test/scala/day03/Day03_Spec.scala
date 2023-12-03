package day03

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import util.InputReader.readInput

class Day03_Spec extends AnyFlatSpec {
  private val day = "day03"
  private val lines = readInput(s"$day/data/input").filter(_.nonEmpty)

  "Puzzle One" should "return correct result" in {
    One.run(lines) shouldBe 4361
  }

  "Puzzle Two" should "return correct result" in {
    Two.run(lines) shouldBe 467835
  }
}
