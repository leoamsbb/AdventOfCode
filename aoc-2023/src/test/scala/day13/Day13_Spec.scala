package day13

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import util.InputReader.readInput

class Day13_Spec extends AnyFlatSpec {
  private val day = "day13"
  private val lines = readInput(s"$day/data/input")
  private val formattedInput = fromInput(lines)

  day should "split the input into two correct data" in {
    val list = fromLineRec("[[1],[2,3,4]]")
    println(list)
  }

  it should "be able to compare two lists" in {
    val list1 = fromLine("[[1],[2,3,4]]")
    val list2 = fromLine("[[1],4]")

    compare(list1, list2) shouldBe true
  }

  it should "be able to compare two lists with only empty parenthesis" in {
    val list1 = fromLine("[[[]]]")
    val list2 = fromLine("[[]]")

    compare(list1, list2) shouldBe true
  }

  "Puzzle One" should "return correct result" in {
    One.run(formattedInput) shouldBe 13
  }

  "Puzzle Two" should "return correct result" in {
    Two.run(lines) shouldBe 0
  }
}
