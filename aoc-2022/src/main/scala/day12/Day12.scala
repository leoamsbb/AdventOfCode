package day12

import util.InputReader.readInput

object Day12 extends App {

  private val day = "day12"
  private val input = readInput(s"$day/data/input")

  private val formattedInput = fromInput(input)

  val opForFirstPuzzle = One.run(formattedInput)
  println(s"Output for first puzzle: $opForFirstPuzzle")

  val opForSecondPuzzle = Two.run(input)
  println(s"Output for Second puzzle: $opForSecondPuzzle")
}
