package day09

object One {

  def run(input: Seq[String]): Int = {

    val head = new Nod()
    val tail = new Nod()
    val instructions = input.map {
      case s"R $steps" => (Right, steps.toInt)
      case s"L $steps" => (Left, steps.toInt)
      case s"U $steps" => (Up, steps.toInt)
      case s"D $steps" => (Down, steps.toInt)
    }

    instructions.foreach { case (instruction, steps) =>
      for {
        _ <- 0 until steps
      } {
        head.move(instruction)
        tail.updateFromNod(head)
      }
    }
    tail.visitedAtLeastOnce()
  }
}
