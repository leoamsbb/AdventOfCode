import scala.annotation.tailrec

package object day10 {

  sealed trait Instruction {
    def timeToExecute: Int

    def numberToAdd: Int
  }

  case class addx(numberToAdd: Int) extends Instruction {
    override def timeToExecute: Int = 2
  }

  case object noop extends Instruction {
    override def timeToExecute: Int = 1

    override def numberToAdd: Int = 0
  }


  def tillNthCycle(instrs: List[Instruction], nthCycle: Int): List[Instruction] = {

    @tailrec
    def loop(acc: List[Instruction], elapsedCycles: Int, toBeProcessed: List[Instruction]): List[Instruction] = {
      val nextRequiredCycles = toBeProcessed.headOption.map(_.timeToExecute).getOrElse(0)
      val projectedCycles = elapsedCycles + nextRequiredCycles
      if (projectedCycles >= nthCycle || toBeProcessed.isEmpty)
        acc
      else
        loop(toBeProcessed.head :: acc, toBeProcessed.head.timeToExecute + elapsedCycles, toBeProcessed.tail)
    }

    loop(Nil, 0, instrs)
  }

  def calculateX(instructions: List[Instruction]): Int = {
    instructions.map(_.numberToAdd).sum + 1
  }

  def fromInput(input: Seq[String]): Seq[Instruction] = {
    input.map {
      case s"addx $number" => addx(number.toInt)
      case s"noop" => noop
    }
  }

  def cycleCoversSprite(cycle: Int, sprite: Int): Boolean =
    cycle <= (sprite + 1) && cycle >= (sprite - 1)
}
