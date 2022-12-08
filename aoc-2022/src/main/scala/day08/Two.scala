package day08

import day08.Input.fromInput

object Two {

  def run(input: Seq[String]): Int = {
    val in = fromInput(input)

    val scenicScores = for {
      row <- 1 until input.length - 1
      col <- 1 until input.head.length - 1
    } yield in.scenicScore(row, col)

    scenicScores.max
  }
}
