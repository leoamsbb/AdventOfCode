package day02

import day02.Outcome.getOutcome
import day02.RPS.fromString

object One {

  def run(input: Seq[String]): Int = {
    input.map(str => {
      val Array(elfMove, myMove) = str.split(" ")
      (fromString(elfMove), fromString(myMove))
    })
      .map(tuple => (tuple._2, getOutcome(tuple._1, tuple._2)))
      .foldLeft(0) { (acc, tuple2) =>
        val (mymove, outcome) = tuple2
        acc + mymove.points() + outcome.outcomePoints()
      }
  }
}
