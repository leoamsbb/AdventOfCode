package day01

object One {

  def run(input: Seq[String]): Int = {
    val (_, max) = input.foldLeft((0, 0)) { (acc, input) =>
      val (current, max) = acc

      input match {
        case "" =>
          (0, max)
        case _ =>
          val calories = input.toInt + current
          (calories, Math.max(calories, max))
      }
    }

    max
  }
}
