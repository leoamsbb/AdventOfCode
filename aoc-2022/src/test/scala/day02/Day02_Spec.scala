package day02

import day02.Outcome.{getOutcome, outcomeFromStr}
import day02.RPS.fromString
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import util.InputReader.readInput

class Day02_Spec extends AnyFlatSpec {
  private val day = "day02"

  "RPS" should "create correct objects given the input" in {
    fromString("A") shouldBe Rock
    fromString("X") shouldBe Rock

    fromString("B") shouldBe Paper
    fromString("Y") shouldBe Paper

    fromString("C") shouldBe Scissors
    fromString("Z") shouldBe Scissors
  }

  "Outcome" should "create correct outcome given the object of RPS" in {
    getOutcome(Rock, Scissors) shouldBe Lost
    getOutcome(Scissors, Paper) shouldBe Lost
    getOutcome(Paper, Rock) shouldBe Lost

    getOutcome(Scissors, Rock) shouldBe Win
    getOutcome(Paper, Scissors) shouldBe Win
    getOutcome(Rock, Paper) shouldBe Win

    getOutcome(Scissors, Scissors) shouldBe Draw
    getOutcome(Rock, Rock) shouldBe Draw
    getOutcome(Paper, Paper) shouldBe Draw
  }

  it should "create correct outcome from input string" in {
    outcomeFromStr("X") shouldBe Lost
    outcomeFromStr("Y") shouldBe Draw
    outcomeFromStr("Z") shouldBe Win
  }

  it should "create correct move depending on the Outcome and elfMove" in {
    Lost.getMyMove(Rock) shouldBe Scissors
    Lost.getMyMove(Scissors) shouldBe Paper
    Lost.getMyMove(Paper) shouldBe Rock

    Draw.getMyMove(Rock) shouldBe Rock
    Draw.getMyMove(Paper) shouldBe Paper
    Draw.getMyMove(Scissors) shouldBe Scissors

    Win.getMyMove(Rock) shouldBe Paper
    Win.getMyMove(Paper) shouldBe Scissors
    Win.getMyMove(Scissors) shouldBe Rock
  }

  private val lines = readInput(s"$day/data/input")
  "Puzzle One" should "return correct result" in {
    One.run(lines) shouldBe 15
  }

  "Puzzle Two" should "return correct result" in {
    Two.run(lines) shouldBe 12
  }
}
