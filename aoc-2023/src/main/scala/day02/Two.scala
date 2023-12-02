package day02


object Two {

  def run(input: Seq[String]): Int = {
    val games = input.filter(_.nonEmpty).map(Game.from)

    def getCount = (game: Game, color: Color) => {
      game.sets.flatMap(_.cubes.filter(_.color == color)).map(_.count).max
    }

    games.map(game => {
      val maxRed = getCount(game, Red)
      val maxGreen = getCount(game, Green)
      val maxBlue = getCount(game, Blue)

      maxRed * maxGreen * maxBlue
    })
      .sum
  }
}
