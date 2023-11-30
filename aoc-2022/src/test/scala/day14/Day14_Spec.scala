package day14


import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers.have
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import util.InputReader.readInput

class Day14_Spec extends AnyFlatSpec {
  private val day = "day14"
  private val lines = readInput(s"$day/data/input")
  private val cave = fromInput(lines)

  day should "split the input into two correct data" in {
    val path = fromString("498,4 -> 498,6 -> 496,6")
    path.lines should have size 2
    path.lines.head shouldBe Line(Pos(498,4), Pos(498,6))
    path.lines.tail.head shouldBe Line(Pos(498,6), Pos(496,6))
  }

  it should "return true if the position is available for sand" in {
    val path = fromString("498,4 -> 498,6 -> 496,6")
//    path.isAvailableForSand(Pos(494, 0)) shouldBe true
  }

  it should "return false if the position is not available for sand" in {
    val path = fromString("498,4 -> 498,6 -> 496,6")
//    path.isAvailableForSand(Pos(498, 5)) shouldBe false
  }

  it should "return correct boundary" in {
    val cave = fromInput(Seq(
      "498,4 -> 498,6 -> 496,6",
      "503,4 -> 502,4 -> 502,9 -> 494,9"
    ))
    val (min, max) = cave.boundary
    min shouldBe Pos(494, 0)
    max shouldBe Pos(503, 9)
  }

  "Puzzle One" should "return correct result" in {
    One.run(cave) shouldBe 24
  }

  "Puzzle Two" should "return correct result" in {
    Two.run(lines) shouldBe 0
  }
}
