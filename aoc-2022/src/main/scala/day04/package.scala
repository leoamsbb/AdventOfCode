package object day04 {

  case class Range(low: Int, high: Int)

  object Range {
    def fromString(input: String) : Range = {
      val Array(first, second) = input.split("-").map(_.toInt)
      Range(first, second)
    }
  }

  case class Pair(elf1: Range, elf2: Range) {
    def overlaps: Boolean = {
      (elf1.low <= elf2.low && elf1.high >= elf2.high) ||
        (elf2.low <= elf1.low && elf2.high >= elf1.high)
    }

    def partialOverlap: Boolean = {
      (elf1.low <= elf2.high && elf1.low >= elf2.low) ||
        (elf2.low <= elf1.high && elf2.low >= elf1.low)
    }
  }

  object Pair {
    def fromString(input: String) : Pair = {
      val Array(elf1, elf2) = input.split(",")
      Pair(Range.fromString(elf1), Range.fromString(elf2))
    }
  }
}
