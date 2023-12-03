package object day03 {

  case class Number(start: Int, end: Int, number: Int)

  private def leftMost(leftIndex: Int): Int = math.max(leftIndex - 1, 0)

  private def rightMost(rightIndex: Int, array: Array[Array[Char]]): Int = math.min(rightIndex + 1, array.head.length-1)

  def CheckNumber(number: (Int, Number), array: Array[Array[Char]])(implicit test: Char => Boolean): Boolean = {
    val (row, aNumber) = number

    checkRow(row - 1, aNumber, array) || checkRow(row + 1, aNumber, array) || {
      val left = leftMost(aNumber.start)
      val right = rightMost(aNumber.end, array)

      test(array(row)(left)) || test(array(row)(right))
    }

  }

  private def checkRow(row: Int, aNumber: Number, array: Array[Array[Char]])(implicit test: (Char => Boolean)): Boolean = {
    if (row < 0 || row >= array.length)
      false
    else {
      val rowOfInput = array(row).slice(leftMost(aNumber.start), rightMost(aNumber.end, array) + 1)
      rowOfInput.exists(test)
    }
  }

  def isSymbol(c: Char): Boolean = !(c.isDigit || c == '.')

  def isGear(c: Char): Boolean = c == '*'

  def fromLine(str: String): List[Number] = {
    val chars = str.toCharArray
    var numbers = List.empty[Option[Number]]

    def loop(start: Int): Option[Number] = {
      var index = start
      var number = ""
      while (index < chars.length && chars(index).isDigit) {
        number = s"$number${chars(index)}"
        index = index + 1
      }
      if (number.nonEmpty) {
        Some(Number(start, index - 1, number.toInt))
      } else None
    }

    var index = 0
    while (index < chars.length) {
      val number = loop(index)
      numbers = numbers :+ number
      index = if (number.nonEmpty)
        number.get.end + 1
      else
        index + 1
    }
    numbers.flatten
  }

}
