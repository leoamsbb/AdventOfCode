package day03

object Two {

  def run(input: Seq[String]): Int = {
    val array = input.map(_.toCharArray).toArray

    val numbers = input.zipWithIndex.flatMap {
      case (inputStr, index) =>
        val nums = fromLine(inputStr)
        nums.map(n => (index, n))
    }.filter(CheckNumber(_, array)(isGear))

    val gearLocation = input.zipWithIndex.flatMap {
      case (inputStr, row) =>
        inputStr.toCharArray.zipWithIndex.filter(_._1 == '*').map {
          case (_, column) => (row, column)
        }
    }

    gearLocation.map {
      case (gearRow, gearColumn) =>
        val numCount = numbers.filter {
          case (numRow, num) =>
            ((gearRow == numRow - 1) || (gearRow == numRow + 1) || (gearRow == numRow)) &&
              (gearColumn >= num.start - 1 && gearColumn <= num.end + 1)

        }
        if (numCount.size == 2)
          numCount.map(_._2).map(_.number).product
        else
          0
    }.sum
  }
}
