package day01

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import scala.io.Source


class Day01_Spec extends AnyFlatSpec {
  private val day = "day01"
  private val lines = Source.fromResource(s"$day/data/input").getLines().toSeq

  "Puzzle One" should "return correct number of calories for given input" in {
    One.run(lines) shouldBe 24000
  }

  "Puzzle Two" should "return correct number of calories for given input" in {
    Two.run(lines :+ "") shouldBe 45000
  }
}
