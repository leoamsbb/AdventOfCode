package day01

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import scala.io.Source


class Day01_Test extends AnyFlatSpec {

  "Puzzle One" should "return correct number of calories for given input" in {
    val lines = Source.fromResource("day01/data/input_one").getLines().toSeq
    One.run(lines) shouldBe 24000
  }

  "Puzzle Two" should "return correct number of calories for given input" in {
    val lines = Source.fromResource("day01/data/input_one").getLines().toSeq
    Two.run(lines :+ "") shouldBe 45000
  }
}
