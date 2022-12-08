package day08

import day08.Input.fromInput
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import util.InputReader.readInput

class Day08_Spec extends AnyFlatSpec {
  private val day = "day08"
  private val lines = readInput(s"$day/data/input")

  private val input = fromInput(lines)

  day should "split the input into two correct data" in {
    input.arr shouldBe List(
      List(3, 0, 3, 7, 3),
      List(2, 5, 5, 1, 2),
      List(6, 5, 3, 3, 2),
      List(3, 3, 5, 4, 9),
      List(3, 5, 3, 9, 0)
    )
  }

  it should "return true if the tree is not hidden from any one side" in {
    input.isVisible(1,1) shouldBe true
    input.isVisible(1,3) shouldBe false
  }

  it should "return correct number of elements that are visible on sides" in {
    input.visibleOnSides() shouldBe 16
  }

  it should "return correct visibility score for a tree" in {
    input.scenicScore(1, 2) shouldBe 4
    input.scenicScore(3, 2) shouldBe 8
  }

  "Puzzle One" should "return correct result" in {
    One.run(lines) shouldBe 21
  }

  "Puzzle Two" should "return correct result" in {
    Two.run(lines) shouldBe 8
  }
}
