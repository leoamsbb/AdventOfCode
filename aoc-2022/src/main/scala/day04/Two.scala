package day04

object Two {

  def run(input: Seq[String]): Int = {
    input.map(Pair.fromString)
      .count(_.partialOverlap)
  }
}
