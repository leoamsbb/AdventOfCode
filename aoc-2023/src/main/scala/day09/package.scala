import scala.math.abs

package object day09 {

  case class Pos(row: Int, col: Int) {
    def moveRight(): Pos = Pos(row, col + 1)

    def moveLeft(): Pos = Pos(row, col - 1)

    def moveUp(): Pos = Pos(row + 1, col)

    def moveDown(): Pos = Pos(row - 1, col)

    def isLeft(other: Pos): Boolean = this.col < other.col

    def isRight(other: Pos): Boolean = this.col > other.col

    def isVerticallyAligned(other: Pos): Boolean = this.col == other.col

    def isUp(other: Pos): Boolean = this.row > other.row

    def isDown(other: Pos): Boolean = this.row < other.row

    def isHorizontallyAligned(other: Pos): Boolean = this.row == other.row

    def isTouching(other: Pos): Boolean = {
      isHorizontallyAligned(other) && abs(this.col - other.col) == 1 ||
        isVerticallyAligned(other) && abs(this.row - other.row) == 1 ||
        (abs(this.col - other.col) == 1 && abs(this.row - other.row) == 1)
    }
  }

  class Nod {

    var pos: Pos = Pos(0, 0)
    var posVisited: List[Pos] = pos :: Nil

    def touching(other: Nod): Boolean = {
      this.pos == other.pos ||
        this.pos.isTouching(other.pos)
    }

    def move(instruction: Instruction): Unit = {
      pos = instruction.move(pos)
      visited(pos)
    }

    private def visited(pos: Pos): Unit = {
      this.pos = pos
      posVisited = pos :: posVisited
    }

    def visitedAtLeastOnce(): Int =
      posVisited.toSet.size

    def updateFromNod(head: Nod): Unit = {
      val headPos = head.pos
      val onSameRow = pos.isHorizontallyAligned(headPos)
      val onSameCol = pos.isVerticallyAligned(headPos)
      if (!touching(head)) {
        val tailLeftOfHead = pos.isLeft(headPos)
        (onSameRow, onSameCol) match {
          case (true, false) => // move right or left
            if (tailLeftOfHead)
              move(Right)
            else
              move(Left)

          case (false, true) => // move up or down
            if (pos.isUp(headPos))
              move(Down)
            else
              move(Up)
          case _ => // move diagonal
            if (pos.isDown(headPos)) {
              if (tailLeftOfHead)
                visited(pos.moveUp().moveRight())
              else
                visited(pos.moveUp().moveLeft())
            } else {
              if (tailLeftOfHead)
                visited(pos.moveDown().moveRight())
              else
                visited(pos.moveDown().moveLeft())
            }
        }
      }
    }
  }

  trait Instruction {
    def move(current: Pos): Pos
  }

  case object Left extends Instruction {
    override def move(current: Pos): Pos = current.moveLeft()
  }

  case object Right extends Instruction {
    override def move(current: Pos): Pos = current.moveRight()
  }

  case object Up extends Instruction {
    override def move(current: Pos): Pos = current.moveUp()
  }

  case object Down extends Instruction {
    override def move(current: Pos): Pos = current.moveDown()
  }

}
