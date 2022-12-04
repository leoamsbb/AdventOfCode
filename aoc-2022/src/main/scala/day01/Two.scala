package day01

object Two {

  def run(input: Seq[String]): Int = {
    val (_, sums) = input.foldLeft((0, List.empty[Int])) { (acc, input) =>
      val (current, sums) = acc

      input match {
        case "" =>
          (0, current :: sums)
        case _ =>
          val calories = input.toInt + current
          (calories, sums)
      }
    }
    sums.sorted.reverse.take(3).sum
  }
}
