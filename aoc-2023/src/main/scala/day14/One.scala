package day14

import scala.annotation.tailrec

object One {

  def run(cave: Cave): Int = {
    var sandPositions = List.empty[Pos]

    var nextSandPosition: Option[Pos] = getNextAvailableSandPositionDown(cave)
    var previousDir:Dir = Up
    while (cave.withinBoundary(nextSandPosition.get) && !sandPositions.contains(nextSandPosition.get)) {
      sandPositions = nextSandPosition.get :: sandPositions

      val probableSandPosition: Option[Pos] = previousDir match {
        case Left =>
          previousDir = Left
          getRightPos(nextSandPosition.get, cave)
        case Right =>
          previousDir = Up
          getUpPos(nextSandPosition.get, cave)
        case Up =>
          previousDir = Left
          getLeftPos(nextSandPosition.get, cave)
      }

      nextSandPosition = probableSandPosition
    }
    sandPositions.size
  }

  private def getNextAvailableSandPositionDown(cave: Cave): Option[Pos] = {

    @tailrec
    def loop(previosPos: Pos, nextPos: Pos): Option[Pos] = {
      if (!cave.isAvailableForSand(nextPos))
        Option(previosPos)
      else if (nextPos.col == cave.getMaxCol())
        None
      else
        loop(nextPos, previosPos.copy(col = previosPos.col + 1))
    }

    loop(Pos(500, 0), Pos(500, 1))
  }

  private def getUpPos(prevSandPosition: Pos, cave: Cave): Option[Pos] = {
    val probablePosition = Pos(prevSandPosition.row, prevSandPosition.col - 1)
    Option(probablePosition)
      .filter(cave.isAvailableForSand)
  }

  private def getLeftPos(prevSandPosition: Pos, cave: Cave): Option[Pos] = {
    val probablePosition = Pos(prevSandPosition.row - 1, prevSandPosition.col)
    Option(probablePosition)
      .filter(cave.isAvailableForSand)
  }

  private def getRightPos(prevSandPosition: Pos, cave: Cave): Option[Pos] = {
    val probablePosition = Pos(prevSandPosition.row + 1, prevSandPosition.col)
    Option(probablePosition)
      .filter(cave.isAvailableForSand)
  }
}
