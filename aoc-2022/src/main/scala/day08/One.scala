package day08

import day08.Input.fromInput

object One {

  def run(input: Seq[String]): Int = {
    val in = fromInput(input)

    val visibilityResult = for {
      row <- 1 until input.length - 1
      col <- 1 until input.head.length - 1
    } yield in.isVisible(row, col)

    in.visibleOnSides + visibilityResult.count(_ == true)
  }
}
