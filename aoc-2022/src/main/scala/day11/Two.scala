package day11

object Two {

  def run(input: List[Monkey]): BigInt = {
    val numOfRounts = 10000
    for {
      round <- 1 to numOfRounts
      monkey <- input
      item <- 0 to monkey.items.size
    } {
      if (round == 21 || round == 1001 || round == 2001 || round == 3001 || round == 4001 ||
        round == 5001 || round == 6001 || round == 7001 || round == 8001 || round == 9001) {
        println(s"Round $round")
        printInspections(input)
      }

      monkey.inspectItemAndMonkeyIsNotBored()
      val canThrow: Option[(Long, Int)] = monkey.testAndThrowTo()
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
