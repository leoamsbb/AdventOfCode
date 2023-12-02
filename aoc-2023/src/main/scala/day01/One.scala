package day01

object One {

  def run(input: Seq[String]): Int = {
    input.map(str => str.toCharArray)
      .map(chars => chars.filter(ch => ch.isDigit))
      .map(chars => s"${chars.head}${chars.last}")
      .map(strNum => strNum.toInt)
      .sum
  }
}
