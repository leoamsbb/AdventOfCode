package day10

object One {

  def run(input: Seq[String]): Int = {
    val instructions = fromInput(input).toList
    val cycles = List(20, 60, 100, 140, 180, 220)

    cycles.map(c => (c, tillNthCycle(instructions, c)))
      .map(tuple => (tuple._1,calculateX(tuple._2)))
      .map(tuple => tuple._1 * tuple._2)
      .sum
  }
}
