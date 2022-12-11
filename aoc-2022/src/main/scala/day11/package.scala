import scala.annotation.tailrec

package object day11 {

  class Monkey(val num: Int,
               var items: List[Long],
               val operation: String,
               val test: Int,
               val throwToIfTrue: Int,
               val throwToIfFalse: Int,
               var inspections: Int = 0
              ) {

    def addItem(worry: Long): Unit =
      items = items :+ worry

    def inspectItem(): Unit = {
      if (items.isEmpty)
        ()
      else {
        inspections = inspections + 1
        val newWorryLevel = applyOperation(items.head)
        val monkeyBoredHenceWorryLevel = newWorryLevel / 3
        items = items.updated(0, monkeyBoredHenceWorryLevel)
      }
    }

    def inspectItemAndMonkeyIsNotBored(): Unit = {
      if (items.isEmpty)
        ()
      else {
        inspections = inspections + 1
        val newWorryLevel = applyOperation(items.head)
        items = items.updated(0, newWorryLevel)
      }
    }

    def testAndThrowTo(): Option[(Long, Int)] = {
      if (items.isEmpty)
        Option.empty
      else {
        val divisible = items.head % test == 0
        Option(
          items.head,
          if (divisible) throwToIfTrue else throwToIfFalse
        )
      }
    }

    def removeItem(): Unit = {
      items = items.tail
    }

    private def applyOperation(currentWorryLevel: Long): Long = {
      operation match {
        case "* old" => currentWorryLevel * currentWorryLevel
        case "+ old" => currentWorryLevel + currentWorryLevel
        case "- old" => 0
        case "/ old" => 1
        case s"* $num" => lcm(currentWorryLevel, num.toLong)
        case s"+ $num" => currentWorryLevel + num.toLong
        case s"- $num" => currentWorryLevel - num.toLong
        case s"/ $num" => currentWorryLevel / num.toLong
      }
    }

  }

  @tailrec
  def gcd(a: Long, b: Long): Long = if (b == 0) a.abs else gcd(b, a % b)

  def lcm(a: Long, b: Long): Long = (a * b).abs / gcd(a, b)

  object Monkey {

    def fromStrings(input: Seq[String]): Monkey = {
      val Seq(num: Int, items: List[Long], operation: String, test: Int, throwToIfTrue: Int, throwToIfFalse: Int) = input
        .map(_.trim)
        .map {
          case s"Monkey $num:" => num.toInt
          case s"Starting items: $listOfItems" => listOfItems.split(",").map(_.trim).map(_.toLong).toList
          case s"Operation: new = old $op" => op
          case s"Test: divisible by $num" => num.toInt
          case s"If true: throw to monkey $monkeyNum" => monkeyNum.toInt
          case s"If false: throw to monkey $monkeyNum" => monkeyNum.toInt
        }

      new Monkey(num, items, operation, test, throwToIfTrue, throwToIfFalse)
    }
  }

  def fromInput(input: Seq[String]): List[Monkey] = {
    input
      .filterNot(_.isBlank)
      .grouped(6)
      .map { group =>
        Monkey.fromStrings(group)
      }
      .toList
  }
}
