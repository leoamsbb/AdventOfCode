package object day05 {

  class Input(numberOfStacks: Int) {
    var stacks: List[List[Char]] = List.fill(numberOfStacks)(Nil)
    var instructions: Seq[String] = Nil

    def updateStack(index: Int, crate: Char): Unit = {
      var stack = stacks(index)
      stack = crate :: stack
      stacks = stacks.updated(index, stack)
    }

    def reverseStacks(): Unit = {
      stacks = stacks.map(list => list.reverse)
    }
  }

  object Input {

    def fromStrings(input: Seq[String]): Input = {
      val whereStacksEnd = input.indexOf("")
      val numberOfStacks = input(whereStacksEnd - 1).split(" ").last.toInt
      val instance = new Input(numberOfStacks)

      (0 until whereStacksEnd).foreach(index => updateStack(input(index), instance))

      instance.instructions = input.drop(whereStacksEnd + 1)
      instance.reverseStacks()
      instance
    }

    private def updateStack(stackEntry: String, instance: Input): Unit = {
      stackEntry.toCharArray
        .grouped(4)
        .toList
        .zipWithIndex
        .foreach {
          case (Array('[', ch, ']'), index) =>
            instance.updateStack(index, ch)
          case (Array('[', ch, ']', ' '), index) =>
            instance.updateStack(index, ch)
          case _ =>
            ()
        }
    }
  }
}
