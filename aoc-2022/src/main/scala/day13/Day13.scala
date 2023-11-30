package day13

import util.InputReader.readInput

object Day13 extends App {

  private val day = "day13"
  private val input = readInput(s"$day/data/input")
  private val formatted = fromInput(input)

  val opForFirstPuzzle = One.run(formatted)
  println(s"Output for first puzzle: $opForFirstPuzzle")

  val opForSecondPuzzle = Two.run(input)
  println(s"Output for Second puzzle: $opForSecondPuzzle")
}
