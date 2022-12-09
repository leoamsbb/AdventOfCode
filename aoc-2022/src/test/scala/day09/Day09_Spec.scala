package day09

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import util.InputReader.readInput

class Day09_Spec extends AnyFlatSpec {
  private val day = "day09"
  private val lines = readInput(s"$day/data/input")
  private val largetInput = readInput(s"$day/data/largerInput")
  val nod1 = new Nod()
  val nod2 = new Nod()

  day should "check whether the nods are touching each other" in {
    nod1.touching(nod2) shouldBe true
  }

  it should "be able to move in correct direction when specified" in {

    val instructions = Seq(
      Right,
      Right,
      Left,
      Up,
      Up,
      Down
    )
    instructions.foreach { in =>
      nod1.move(in)
    }
    nod1.pos shouldBe Pos(1, 1)
  }

  it should "be able to track how many positions visited" in {
    nod1.visitedAtLeastOnce() shouldBe 5
  }

  "Puzzle One" should "return correct result" in {
    One.run(lines) shouldBe 13
  }

  "Puzzle Two" should "return correct result" in {
    Two.run(lines) shouldBe 1
    Two.run(largetInput) shouldBe 36
  }
}
