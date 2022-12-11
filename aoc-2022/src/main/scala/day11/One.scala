package day11

object One {

  def run(input: List[Monkey]): Int = {
    val numOfRounts = 20
    for {
      round <- 1 to numOfRounts
      monkey <- input
      item <- 0 to monkey.items.size
    } {
      monkey.inspectItem()
      val canThrow = monkey.testAndThrowTo()
      canThrow match {
        case Some(tuple) =>
          val (newWorryLevel, throwTo) = tuple
          monkey.removeItem()
          input(throwTo).addItem(newWorryLevel)
        case None => ()
      }
    }
    input.map(_.inspections).sorted.reverse.take(2).product
  }
}
