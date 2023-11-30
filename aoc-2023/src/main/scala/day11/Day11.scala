package day11

import util.InputReader.readInput

object Day11 extends App {

  private val day = "day11"
  private val input = readInput(s"$day/data/input")
  private val monkeys = fromInput(input)

  val opForFirstPuzzle = One.run(monkeys)
  println(s"Output for first puzzle: $opForFirstPuzzle")

  val opForSecondPuzzle = Two.run(monkeys)
  println(s"Output for Second puzzle: $opForSecondPuzzle")
}
