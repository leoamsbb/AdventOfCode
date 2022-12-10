package day10

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers.contain
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import util.InputReader.readInput

class Day10_Spec extends AnyFlatSpec {
  private val day = "day10"
  private val lines = readInput(s"$day/data/input")

  private val instructions = fromInput(lines).toList

  day should "correct milestones for nth cycle" in {
    calculateX(tillNthCycle(instructions, 20)) shouldBe 21
    calculateX(tillNthCycle(instructions, 60)) shouldBe 19
    calculateX(tillNthCycle(instructions, 100)) shouldBe 18
    calculateX(tillNthCycle(instructions, 140)) shouldBe 21
    calculateX(tillNthCycle(instructions, 180)) shouldBe 16
    calculateX(tillNthCycle(instructions, 220)) shouldBe 18
  }

  "Puzzle One" should "return correct result" in {
    One.run(lines) shouldBe 13140
  }

  "Puzzle Two" should "return correct result" in {
    Two.run(lines) should contain theSameElementsInOrderAs List(
      "##..##..##..##..##..##..##..##..##..##..",
      "###...###...###...###...###...###...###.",
      "####....####....####....####....####....",
      "#####.....#####.....#####.....#####.....",
      "######......######......######......####",
      "#######.......#######.......#######....."
    )
  }
}
