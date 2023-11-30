package object day08 {
  class Input(rows: Int, cols: Int) {

    var arr: Array[Array[Int]] = Array.ofDim[Int](rows, cols)

    private def addToList(index: Int, list: Array[Int]): Unit =
      arr = arr.updated(index, list)

    private def getCol(col: Int) = arr.map {
      _ (col)
    }

    private def getLeftRight(row: Int, col: Int) = (arr(row).slice(0, col), arr(row).slice(col + 1, cols))

    private def getUpDown(row: Int, col: Int) = {
      val column = getCol(col)
      (column.slice(0, row), column.slice(row + 1, rows))
    }

    def scenicScore(row: Int, col: Int): Int = {
      val elem = arr(row)(col)
      val (left, right) = getLeftRight(row, col)
      val (up, down) = getUpDown(row, col)

      def getScore(arr: Array[Int]) = {
        val index = arr.indexWhere(_ >= elem)
        if (index == -1)
          arr.length
        else
          index + 1
      }

      val leftScore = getScore(left.reverse)
      val rightScore = getScore(right)
      val upScore = getScore(up.reverse)
      val downScore = getScore(down)

      leftScore * rightScore * upScore * downScore
    }

    def isVisible(row: Int, col: Int): Boolean = {
      val elem = arr(row)(col)
      val (left, right) = getLeftRight(row, col)
      val (up, down) = getUpDown(row, col)
      left.forall(_ < elem) || right.forall(_ < elem) ||
        up.forall(_ < elem) || down.forall(_ < elem)
    }

    def visibleOnSides(): Int = (arr.head.length * 2) + ((arr.length - 2) * 2)

  }

  object Input {
    def fromInput(input: Seq[String]): Input = {
      val in = new Input(input.length, input.head.length)

      input.zipWithIndex.foreach {
        case (str, index) => in.addToList(index, str.toCharArray.map(_ - '0'))
      }
      in
    }
  }
}
