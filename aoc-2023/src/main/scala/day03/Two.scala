package day03

object Two {

  def run(input: Seq[String]): Int = {
    input.grouped(3)
      .map(group => badge(group))
      .map(priority)
      .sum
  }
}
