package day01

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import scala.io.Source


class Day01_Spec extends AnyFlatSpec {
  private val day = "day01"
  private val lines1 = Source.fromResource(s"$day/data/input").getLines().toSeq
  private val lines2 = Source.fromResource(s"$day/data/input_02").getLines().toSeq

  "Puzzle One" should "return correct number of calories for given input" in {
    One.run(lines1) shouldBe 142
  }

  "Puzzle Two" should "return correct number of calories for given input" in {
    Two.run(lines2 :+ "") shouldBe 281
  }
}
