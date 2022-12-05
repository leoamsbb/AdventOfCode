package day05

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers.{contain, have}
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import util.InputReader.readInput

class Day05_Spec extends AnyFlatSpec {
  private val day = "day05"
  private val lines = readInput(s"$day/data/input")

  day should "split the input into two correct data" in {
    val input = Input.fromStrings(lines)

    input.stacks should have size 3
    input.stacks should contain theSameElementsAs List(
      List('N','Z'),
      List('D', 'C', 'M'),
      List('P')
    )

    input.instructions should have size 4
  }

  "Puzzle One" should "return correct result" in {
    One.run(lines) shouldBe "CMZ"
  }

  "Puzzle Two" should "return correct result" in {
    Two.run(lines) shouldBe "MCD"
  }
}
