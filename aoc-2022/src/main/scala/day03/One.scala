package day03

object One {

  def run(input: Seq[String]): Int = {
    input.map(splitInput)
      .map(misplacedItem)
      .map(priority)
      .sum
  }
}
