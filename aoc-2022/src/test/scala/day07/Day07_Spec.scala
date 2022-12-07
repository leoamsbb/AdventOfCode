package day07

import org.scalatest.Ignore
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import util.InputReader.readInput

class Day07_Spec extends AnyFlatSpec {
  private val day = "day07"
  private val lines = readInput(s"$day/data/input")

  day should "split the input into two correct data" in {

  }

  "Puzzle One" should "return correct result" in {
    One.run(lines) shouldBe 95437
  }

  "Puzzle Two" should "return correct result" in {
    Two.run(lines) shouldBe 24933642
  }
}
