package day06

import util.InputReader.readInput

object Day06 extends App {

  private val day = "day06"
  private val input = readInput(s"$day/data/input")

  val opForFirstPuzzle = One.run(input)
  println(s"Output for first puzzle: $opForFirstPuzzle")

  val opForSecondPuzzle = Two.run(input)
  println(s"Output for Second puzzle: $opForSecondPuzzle")
}
