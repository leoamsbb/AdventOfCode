package dayXX

import util.InputReader.readInput

object DayXX extends App {

  private val day = "dayXX"
  private val input = readInput(s"$day/data/input")

  val opForFirstPuzzle = One.run(input)
  println(s"Output for first puzzle: $opForFirstPuzzle")

  val opForSecondPuzzle = Two.run(input)
  println(s"Output for Second puzzle: $opForSecondPuzzle")
}
