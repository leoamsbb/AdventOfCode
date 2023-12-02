package day02

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import util.InputReader.readInput


class Day02_Spec extends AnyFlatSpec {
  private val day = "day02"
  private val lines = readInput(s"$day/data/input").filter(_.nonEmpty)

  "Game" should "be able to parse a String and return game object" in {
    Game.from("Game 11:") shouldBe Game(11, List.empty)
    Game.from("Game 15:") shouldBe Game(15, List.empty)
  }

  "Color" should "be able to parse a String and return color object" in {
    Color.from("red") shouldBe Red
    Color.from("blue") shouldBe Blue
    Color.from("green") shouldBe Green
  }

  "Cube" should "be able to parse a String and return cube object" in {
    Cube.from("1 red") shouldBe Cube(1, Red)
    Cube.from("15  blue") shouldBe Cube(15, Blue)
    Cube.from("4 Green") shouldBe Cube(4, Green)
  }

  "Program" should "be able to parse a line and prepare set" in {
    val line = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
    Game.from(line) shouldBe Game(1, List(
      CubeSet(List(Cube(3, Blue), Cube(4, Red))),
      CubeSet(List(Cube(1, Red), Cube(2, Green), Cube(6, Blue))),
      CubeSet(List(Cube(2, Green)))
    ))
  }

  "Puzzle One" should "return correct result" in {
    One.run(lines) shouldBe 8
  }

  "Puzzle Two" should "return correct result" in {
    Two.run(lines) shouldBe 2286
  }
}
