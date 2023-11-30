package day18

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import util.InputReader.readInput

class Day18_Spec extends AnyFlatSpec {
  private val day = "day18"
  private val lines = readInput(s"$day/data/input")
  private val formattedInput = fromInput(lines)

  day should "split the input into two correct data" in {
    val actual = fromInput(List("2,2,2"))
    actual.head shouldBe LavaDroplet(2,2,2)
  }

  it should "identify number of open surfaces correctly given two cubes" in {
    val List(droplet1, droplet2) = List(
      LavaDroplet(1,1,1),
      LavaDroplet(2,1,1)
    )

    updateSurfacesTouching(droplet1, droplet2) shouldBe YZ
  }

  "Puzzle One" should "return correct result" in {
    One.run(formattedInput) shouldBe 64
  }

  "Puzzle Two" should "return correct result" in {
    Two.run(lines) shouldBe 0
  }
}
