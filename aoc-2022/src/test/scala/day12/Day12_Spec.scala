package day12

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import util.InputReader.readInput

class Day12_Spec extends AnyFlatSpec {
  private val day = "day12"
  private val lines = readInput(s"$day/data/input")
  private val formattedInput = fromInput(lines)

  day should "split the input into two correct data" in {

  }

  "Puzzle One" should "return correct result" in {
    One.run(formattedInput) shouldBe 31
  }

  "Puzzle Two" should "return correct result" in {
    Two.run(lines) shouldBe 0
  }
}
