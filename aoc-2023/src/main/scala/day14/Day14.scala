package day14

import util.InputReader.readInput

object Day14 extends App {

  private val day = "day14"
  private val input = readInput(s"$day/data/input")
  private val cave = fromInput(input)

  val opForFirstPuzzle = One.run(cave)
  println(s"Output for first puzzle: $opForFirstPuzzle")

  val opForSecondPuzzle = Two.run(input)
  println(s"Output for Second puzzle: $opForSecondPuzzle")
}
