package day01

import util.InputReader.readInput

object Main extends App {

  private val input = readInput("day01/data/input_one")
  val opForFirstPuzzle = One.run(input)
  println(s"Output for first puzzle: $opForFirstPuzzle")

  val opForSecondPuzzle = Two.run(input)
  println(s"Output for Second puzzle: $opForSecondPuzzle")
}
