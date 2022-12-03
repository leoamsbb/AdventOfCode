package day02

import day02.Outcome.{getOutcome, outcomeFromStr}
import day02.RPS.fromString

object Two {

  def run(input: Seq[String]): Int = {
    input.map(str => {
      val Array(elfMove, intendedOutcome) = str.split(" ")
      (fromString(elfMove), outcomeFromStr(intendedOutcome))
    })
      .map {
        case (elfMove, outcome) => (outcome.getMyMove(elfMove), outcome)
      }
      .foldLeft(0) { (acc, tuple2) =>
        val (myMove, outcome) = tuple2
        acc + myMove.points() + outcome.outcomePoints()
      }
  }
}
