package day07

import util.InputReader.readInput

object Day07 extends App {

  private val day = "day07"
  private val input = readInput(s"$day/data/input")

  val opForFirstPuzzle = One.run(input)
  println(s"Output for first puzzle: $opForFirstPuzzle")

  val opForSecondPuzzle = Two.run(input)
  println(s"Output for Second puzzle: $opForSecondPuzzle")
}
