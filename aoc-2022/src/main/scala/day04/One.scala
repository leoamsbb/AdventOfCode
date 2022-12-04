package day04

object One {
  def run(input: Seq[String]): Int = {
    input.map(Pair.fromString)
      .count(_.overlaps)
  }
}
