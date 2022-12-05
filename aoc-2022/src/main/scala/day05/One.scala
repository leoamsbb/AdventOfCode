package day05

object One {

  def run(input: Seq[String]): String = {
    val formattedInput = Input.fromStrings(input)
    formattedInput.instructions
      .foreach { case s"move $numberOfCrates from $source to $dest" =>
        val fromStackIndex = source.toInt - 1
        val toStackIndex = dest.toInt - 1
        var fromStack = formattedInput.stacks(fromStackIndex)
        var toStack = formattedInput.stacks(toStackIndex)

        (0 until numberOfCrates.toInt)
          .foreach(_ => {
            val elem = fromStack.head
            toStack = elem :: toStack
            fromStack = fromStack.tail
          })

        formattedInput.stacks = formattedInput.stacks.updated(fromStackIndex, fromStack)
        formattedInput.stacks = formattedInput.stacks.updated(toStackIndex, toStack)
      }

    formattedInput.stacks.map(_.head).mkString
  }
}
