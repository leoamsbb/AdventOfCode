package day07

object One {

  def run(input: Seq[String]): Long = {
    listOfDirs(input)
      .map(_.size)
      .filter(_ <= 100000)
      .sum
  }
}
