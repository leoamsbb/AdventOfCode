import scala.language.implicitConversions

package object day02 {

  case class Game(id: Int, sets: List[CubeSet] = List.empty)
  object Game {
    def from(input: String) : Game= {
      input match {
        case s"Game$id:$rest" =>
          val sets = rest.trim.split(";").toList.filter(_.nonEmpty)
          val cubeSets = sets.map(_.trim).map(CubeSet.from)
          Game(id.trim.toInt, cubeSets)
        case _ => throw new IllegalArgumentException("Case not matched in Game")
      }
    }
  }

  sealed trait Color
  case object Red extends Color
  case object Blue extends Color
  case object Green extends Color
  object Color {
    def from(str: String): Color = str.toLowerCase match {
      case "blue" =>  Blue
      case "red" => Red
      case "green" => Green
    }
  }

  case class Cube(count: Int, color: Color)
  object Cube {
    def from(str: String): Cube = str match {
      case s"$count $color" => Cube(count = count.trim.toInt, color = Color.from(color.trim))
      case _ => throw new IllegalArgumentException("Case not matched in Cube")
    }

    def withinLimit(cube: Cube): Boolean = cube.color match {
      case Red => cube.count <= 12
      case Blue => cube.count <= 14
      case Green => cube.count <= 13
    }
  }

  case class CubeSet(cubes: List[Cube])
  object CubeSet {
    def from(string: String) : CubeSet = {
      val cubes = string.trim.split(",").toList
      CubeSet(
        cubes
        .map(_.trim)
        .map(Cube.from))
    }
  }
}
