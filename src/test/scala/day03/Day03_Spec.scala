package day03

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import util.InputReader.readInput

class Day03_Spec extends AnyFlatSpec {
  private val lines = readInput("day03/data/input")

  "Day 03" should "split the input into two corresponding compartments" in {
    val (comp1, comp2) = splitInput("vJrwpWtwJgWrhcsFMMfFFhFp")
    comp1 shouldBe "vJrwpWtwJgWr"
    comp2 shouldBe "hcsFMMfFFhFp"
  }

  it should "return misplaced item" in {
    misplacedItem(splitInput("vJrwpWtwJgWrhcsFMMfFFhFp")) shouldBe 'p'
  }

  it should "return correct priority" in {
    priority('p') shouldBe 16
    priority('L') shouldBe 38
    priority('P') shouldBe 42
    priority('v') shouldBe 22
    priority('t') shouldBe 20
    priority('s') shouldBe 19
  }

  it should "return correct badge for a group of three" in {
    badge(Seq("vJrwpWtwJgWrhcsFMMfFFhFp", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL", "PmmdzqPrVvPwwTWBwg")) shouldBe 'r'
    badge(Seq("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn", "ttgJtRGJQctTZtZT", "CrZsJsPPZsGzwwsLwLmpwMDw")) shouldBe 'Z'
  }

  "Puzzle One" should "return correct result" in {
    One.run(lines) shouldBe 157
  }

  "Puzzle Two" should "return correct result" in {
    Two.run(lines) shouldBe 70
  }
}
