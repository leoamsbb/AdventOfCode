package day10

object Two {

  def run(input: Seq[String]): Seq[String] = {
    val instructions = fromInput(input).toList

    val screen = for {
      cycle <- 1 to 240
    } yield {
      val instructionsTillCycle = tillNthCycle(instructions, cycle)
      val sprite = calculateX(instructionsTillCycle)
      if (cycleCoversSprite((cycle - 1) % 40, sprite))
        '#'
      else
        '.'
    }

    screen.toList.grouped(40).toList.map(_.mkString(""))
  }
}
