package day09

object Two {

  def run(input: Seq[String]): Int = {
    val head = new Nod()
    val tail = List.fill(9)(new Nod())
    val instructions = input.map {
      case s"R $steps" => (Right, steps.toInt)
      case s"L $steps" => (Left, steps.toInt)
      case s"U $steps" => (Up, steps.toInt)
      case s"D $steps" => (Down, steps.toInt)
    }

    instructions.foreach { case (instruction, steps) =>
      (0 until steps)
        .foreach(_ => {
          head.move(instruction)
          tail.head.updateFromNod(head)
          for {
            index <- 1 until tail.size
          } tail(index).updateFromNod(tail(index - 1))
        })
    }
    tail(8).visitedAtLeastOnce()
  }
}
