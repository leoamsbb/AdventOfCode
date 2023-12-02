package day02

import day02.Cube.withinLimit

object One {

  def run(input: Seq[String]): Int = {
    val games = input.filter(_.nonEmpty).map(Game.from)
    games
      .filter(game => {
        game.sets.forall(set => set.cubes.forall(withinLimit))
      })
      .map(_.id)
      .sum
  }
}
