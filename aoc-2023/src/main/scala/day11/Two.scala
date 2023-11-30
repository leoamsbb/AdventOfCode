package day11

object Two {

  def run(input: List[Monkey]): BigInt = {
    val numOfRounds = 10000
    for {
      _ <- 1 to numOfRounds
      monkey <- input
      _ <- monkey.items.indices
    } {
      monkey.inspectItemAndMonkeyIsNotBored()
      val canThrow = monkey.testAndThrowTo()
      canThrow match {
        case Some(tuple) =>
          val (newWorryLevel, throwTo) = tuple
          monkey.removeItem()
          input(throwTo).addItem(newWorryLevel)
        case None => ()
      }
    }

    printInspections(input)
    input.map(_.inspections).map(BigInt(_)).sorted.reverse.take(2).product
  }

  def printInspections(input: List[Monkey]): Unit = {
    input.foreach(monkey => println(s" Monkey ${monkey.num} Inspections: ${monkey.inspections}"))
    println("--------------------------------------------------------------------------------")
  }
}
