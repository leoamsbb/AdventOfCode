package day11

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers.contain
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import util.InputReader.readInput

class Day11_Spec extends AnyFlatSpec {
  private val day = "day11"
  private val lines = readInput(s"$day/data/input")
  private val monkeys = fromInput(lines)

  day should "split the input into two correct data" in {
    verifyMonkey(monkeys.head, new Monkey(0, List(79, 98), "* 19", 23, 2, 3))
    verifyMonkey(monkeys.tail.head, new Monkey(1, List(54, 65, 75, 74), "+ 6", 19, 2, 0))
  }

  it should "be able to append item in monkey's list" in {
    val monkey = new Monkey(0, List(79, 98), "* 19", 23, 2, 3)
    monkey.addItem(50)
    verifyMonkey(monkey, new Monkey(0, List(79, 98, 50), "* 19", 23, 2, 3))
  }

  "Puzzle One" should "return correct result" in {
    One.run(monkeys) shouldBe 10605
  }

  "Puzzle Two" should "return correct result" in {
    Two.run(monkeys) shouldBe BigInt("2713310158")
  }

  private def verifyMonkey(actual: Monkey, expected: Monkey): Unit = {
    actual.num shouldBe expected.num
    actual.items should contain theSameElementsAs expected.items
    actual.operation shouldBe expected.operation
    actual.test shouldBe expected.test
    actual.throwToIfTrue shouldBe expected.throwToIfTrue
    actual.throwToIfFalse shouldBe expected.throwToIfFalse
  }
}
