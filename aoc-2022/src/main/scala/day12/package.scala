import scala.collection.mutable

package object day12 {

  case class Pos(row: Int, col: Int)

  case class Elevation(height: Char, pos: Pos)

  object Elevation {
    implicit def fromPosAndHeight(pos: Pos, height: Char) = {
      Elevation(height, pos)
    }
  }

  case class Hill(current: Pos, highestElevation: Pos)

  def getNode(input: List[List[Elevation]], withHeight: Int): Pos =
    input.flatMap(_.filter(_.height == withHeight)).map(_.pos).head

  var maxRow = 0
  var maxCol = 0

  def dijkstra1(graph: Map[Pos, Char])(source: Pos): (Map[Pos, Int], Map[Pos, Pos]) = {
    maxRow = graph.keys.map(_.row).max
    maxCol = graph.keys.map(_.col).max

    val active = mutable.Set(source)
    val res = mutable.Map(source -> 0)
    val pred = mutable.Map.empty[Pos, Pos]
    while (active.nonEmpty) {
      val node = active.minBy(res)
      active -= node
      val cost = res(node)
      for ((pos, _) <- getConnectedNodes(graph, node)) {
        val cost1 = cost + 1
        if (cost1 < res.getOrElse(pos, Int.MaxValue)) {
          active += pos
          res += (pos -> cost1)
          pred += (pos -> node)
        }
      }
    }
    (res.toMap, pred.toMap)
  }


  def withinBoundaries(pos: Pos): Boolean = {
    val result = pos.row >= 0 &&
      pos.col >= 0 &&
      pos.row <= maxRow &&
      pos.col <= maxCol
    result
  }

  def getConnectedNodes(graph: Map[Pos, Char], node: Pos) = {
    val (row, col) = Pos.unapply(node).get
    val currentNodeHeight = graph(node)
    val positions = List(Pos(row, col - 1), Pos(row, col + 1), Pos(row + 1, col), Pos(row - 1, col))
      .filter(pos => withinBoundaries(pos))
    val connectedNodes = positions.map(p => (p, graph(p)))
    if (connectedNodes.exists(_._2 == 'E'))
      println("Encountered End Node")

    val connectedNodesYouCanTravel = connectedNodes
      .filter(entry => {
        val entryHeight = entry._2
        if (currentNodeHeight == 'S' && entryHeight == 'a')
          true
        else if (currentNodeHeight == 'z' && entryHeight == 'E')
          true
        else
          entryHeight - currentNodeHeight == 1 || entryHeight - currentNodeHeight == 0
      }
      )

    if (connectedNodes.exists(_._2 == 'E'))
      println("Encountered End Node in connectedNodesYOuCanTravel")
    connectedNodesYouCanTravel
  }

  def canTraverse(source: Elevation, dest: Elevation): Boolean = {
    (source.height - dest.height).abs == 1
  }

  def fromInput(input: Seq[String]): List[List[Elevation]] = {
    input
      .toList
      .zipWithIndex
      .map {
        case (str, row) =>
          str.toCharArray
            .toList
            .zipWithIndex
            .map {
              case (height, col) => Elevation(height, Pos(row, col))
            }
      }
  }

}
