package day13

object One {

  def run(input: Seq[List[NestedIntElem]]): Int = {
    val indexes = input.grouped(2)
      .zipWithIndex
      .map {
        case (group, index) =>
          val list1 = group.head
          val list2 = group.tail.head

          (compare(list1, list2), index)
      }
      .filter(tuple => tuple._1)
      .map(_._2)
      .map(_ + 1)
      .toList

    println(s"indexes: ${indexes}")
    val result = indexes.sum
    println(s"result: $result")
    result
  }
}
