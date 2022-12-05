package day05

object Two {

  def run(input: Seq[String]): String = {
    val formattedInput = Input.fromStrings(input)

    formattedInput.instructions
      .foreach { case s"move $numberOfCratesStr from $source to $dest" =>
        val fromStackIndex = source.toInt - 1
        val toStackIndex = dest.toInt - 1
        val numberOfCrates = numberOfCratesStr.toInt
        var fromStack = formattedInput.stacks(fromStackIndex)
        var toStack = formattedInput.stacks(toStackIndex)

        if (numberOfCrates == 1) {
          val elem = fromStack.head
          toStack = elem :: toStack
          fromStack = fromStack.tail
        } else {
          val elements = fromStack.slice(0, numberOfCrates)
          toStack = elements ::: toStack
          fromStack = fromStack.drop(numberOfCrates)
        }
        formattedInput.stacks = formattedInput.stacks.updated(fromStackIndex, fromStack)
        formattedInput.stacks = formattedInput.stacks.updated(toStackIndex, toStack)
      }

    formattedInput.stacks
      .map(_.head)
      .mkString
  }
}
