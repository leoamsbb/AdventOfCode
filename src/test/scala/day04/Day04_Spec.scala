package day04

import day04.Range.fromString
import day04.Pair.{fromString => pairFromString}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import util.InputReader.readInput

class Day04_Spec extends AnyFlatSpec {
  private val day = "day04"
  private val lines = readInput(s"$day/data/input")

  day should "split the input into two correct data" in {
    fromString("2-4") shouldBe Range(2,4)
  }

  it should "create proper ranges for pair" in {
    val pair = pairFromString("2-4,6-8")
    pair.elf1 shouldBe Range(2,4)
    pair.elf2 shouldBe Range(6,8)
  }

  it should "identify a pair which completely overlaps the other" in {
    pairFromString("2-8,3-7").overlaps shouldBe true
    pairFromString("6-6,4-6").overlaps shouldBe true
    pairFromString("2-4,6-8").overlaps shouldBe false
    pairFromString("2-3,4-5").overlaps shouldBe false
  }

  it should "identify the pairs which partially overlap" in {
    pairFromString("5-7,7-9").partialOverlap shouldBe true
    pairFromString("2-8,3-7").partialOverlap shouldBe true
    pairFromString("6-6,4-6").partialOverlap shouldBe true
    pairFromString("2-6,4-8").partialOverlap shouldBe true
    pairFromString("2-4,6-8").partialOverlap shouldBe false
    pairFromString("2-3,4-5").partialOverlap shouldBe false
  }

  "Puzzle One" should "return correct result" in {
    One.run(lines) shouldBe 2
  }

  "Puzzle Two" should "return correct result" in {
    Two.run(lines) shouldBe 4
  }
}
