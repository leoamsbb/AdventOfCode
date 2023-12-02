package day01

object Two {

  def run(input: Seq[String]): Int = {

    val firstDigits = input
      .filter(str => str.nonEmpty)
      .map( str => getFirstDigit(str))

    val lastDigits = input
      .filter(str => str.nonEmpty)
      .map(str => getLastDigit(str))

    val result = firstDigits.map(_._2).zip(lastDigits)
      .foldLeft(0) {
      case (acc, (first, last)) =>
        val number = s"$first$last".toInt
        acc + number
    }

    result
  }

  private def getFirstDigit(input: String) = {

    def loop(start: Int, digit: Char, found: Boolean ): (Int, Char) = {
      if (found)
        (start, digit)
      else {
        val subInput = input.substring(start)
        val (size, digit, f) = subInput match {
          case _ if subInput.startsWith("one") => (3, '1', true)
          case _ if subInput.startsWith("two") => (3, '2', true)
          case _ if subInput.startsWith("three") => (5, '3', true)
          case _ if subInput.startsWith("four") => (4, '4', true)
          case _ if subInput.startsWith("five") => (4, '5', true)
          case _ if subInput.startsWith("six") => (3, '6', true)
          case _ if subInput.startsWith("seven") => (5, '7', true)
          case _ if subInput.startsWith("eight") => (5, '8', true)
          case _ if subInput.startsWith("nine") => (4, '9', true)
          case _ if subInput.head.isDigit => (1, subInput.head, true)
          case _ => (0, 'A', false)
        }
        loop(start + (if (size == 0) 1 else size), digit, f)
      }
    }

    loop(0,'A', false)
  }

  private def getLastDigit(input: String) = {

    def loop(end: Int, digit: Char, found: Boolean): Char = {
      if (found)
        digit
      else {
        val subInput = input.substring(0, end)
        val (size, digit, f) = subInput match {
          case _ if subInput.endsWith("one") => (3, '1', true)
          case _ if subInput.endsWith("two") => (3, '2', true)
          case _ if subInput.endsWith("three") => (5, '3', true)
          case _ if subInput.endsWith("four") => (4, '4', true)
          case _ if subInput.endsWith("five") => (4, '5', true)
          case _ if subInput.endsWith("six") => (3, '6', true)
          case _ if subInput.endsWith("seven") => (5, '7', true)
          case _ if subInput.endsWith("eight") => (5, '8', true)
          case _ if subInput.endsWith("nine") => (4, '9', true)
          case _ if subInput.last.isDigit => (1, subInput.last, true)
          case _ => (0, 'A', false)
        }
        loop(end - (if (size == 0) 1 else size), digit, f)
      }
    }

    loop(input.length, 'A', false)
  }
}
