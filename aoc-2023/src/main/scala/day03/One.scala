package day03


object One {

  def run(input: Seq[String]): Int = {
    val array = input.map(_.toCharArray).toArray

    input.zipWithIndex.flatMap {
      case (input, index) =>
        val nums = fromLine(input)
        nums.map(n => (index, n))
    }.filter(n => CheckNumber(n, array)(isSymbol))
      .map(_._2)
      .map(_.number)
      .sum
  }
}
