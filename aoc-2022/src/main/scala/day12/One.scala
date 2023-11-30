package day12

object One {

  def run(input: List[List[Elevation]]): Int = {

    val graph = input.flatMap { row => {
      row.map { col => {
        (col.pos, col.height)
      }}
    }}.toMap


    val sourceNode = getNode(input, 'S')
    val destNode = getNode(input, 'E')
    val (result, anotherMap) = dijkstra1(graph)(sourceNode)

//    result.foreach(println)

    result.getOrElse(destNode, 0)
  }
}
