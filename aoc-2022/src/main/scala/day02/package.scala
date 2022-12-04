package object day02 {

  sealed trait RPS {
    def points(): Int
  }

  object RPS {
    def fromString(input: String): RPS = input match {
      case "A" | "X" => Rock
      case "B" | "Y" => Paper
      case "C" | "Z" => Scissors
    }
  }

  case object Rock extends RPS {
    override val points: Int = 1
  }

  case object Paper extends RPS {
    override val points: Int = 2
  }

  case object Scissors extends RPS {
    override val points: Int = 3
  }

  sealed trait Outcome {
    def outcomePoints(): Int

    def getMyMove(elfMove: RPS): RPS
  }

  object Outcome {
    def outcomeFromStr(input: String): Outcome = {
      input match {
        case "X" => Lost
        case "Y" => Draw
        case "Z" => Win
      }
    }
    def getOutcome(elf1Move: RPS, myMove: RPS): Outcome = {
      (elf1Move, myMove) match {
        case (Scissors, Rock) => Win
        case (Paper, Scissors) => Win
        case (Rock, Paper) => Win
        case (m1, m2) if m1 == m2 => Draw
        case _ => Lost
      }
    }
  }

  case object Lost extends Outcome {
    override val outcomePoints: Int = 0

    override def getMyMove(elfMove: RPS): RPS = elfMove match {
      case Rock => Scissors
      case Paper => Rock
      case Scissors => Paper
    }
  }

  case object Draw extends Outcome {
    override val outcomePoints: Int = 3

    override def getMyMove(elfMove: RPS): RPS = elfMove
  }

  case object Win extends Outcome {
    override val outcomePoints: Int = 6

    override def getMyMove(elfMove: RPS): RPS = elfMove match {
      case Rock => Paper
      case Paper => Scissors
      case Scissors => Rock
    }
  }

}
