package object day14 {

  sealed trait Dir
  case object Up extends Dir
  case object Left extends Dir
  case object Right extends Dir

  case class Pos(row: Int, col: Int)

  case class Line(from: Pos, to: Pos) {

    def points(): List[Pos] = List(from, to)

    def isRockAt(pos: Pos) : Boolean = {
      isBetween(pos.row, _.row) &&
        isBetween(pos.col, _.col)
    }

    private def isBetween(value: Int, mapper: Pos => Int): Boolean = {
      val fromVal = mapper(from)
      val toVal = mapper(to)

      val (min, max) = (Math.min(fromVal, toVal), Math.max(fromVal, toVal))
      value <= max && value >= min
    }
  }

  case class Path(lines: List[Line])

  case class Cave(paths: List[Path]) {
    val boundary: (Pos, Pos) = {
      val rows = paths.flatMap(_.lines).flatMap(_.points()).map(_.row)
      val cols = paths.flatMap(_.lines).flatMap(_.points()).map(_.col)
      val minRow = rows.min
      val maxRow = rows.max
      val maxCol = cols.max

      (Pos(minRow, 0), Pos(maxRow, maxCol))
    }

    def withinBoundary(pos: Pos): Boolean = {
      val (min, max) = boundary
      pos.row >= min.row && pos.col >= min.col &&
        pos.row <= max.row && pos.col <= max.col
    }

    def getMaxCol(): Int = {
      boundary._2.col
    }

    def getMaxRow(): Int = {
      boundary._2.row
    }

    def isAvailableForSand(pos: Pos) = !paths.flatMap(_.lines).exists(_.isRockAt(pos))
  }

  def fromInput(input: Seq[String]): Cave =
    Cave(input
      .toList
      .map(fromString))

  def fromString(str: String) = {
    val lines = str.split("->")
      .map(_.trim)
      .map {
        case s"$row,$col" => Pos(row.toInt, col.toInt)
      }.toList.sliding(2)
      .map {
        case List(start, end) => Line(start, end)
      }
      .toList

    Path(lines)
  }
}
