package object day18 {

  sealed trait Sides
  case object XY extends Sides
  case object YZ extends Sides
  case object ZX extends Sides

  case class LavaDroplet(x:Int, y:Int, z:Int) {
    def isXCommon(other: LavaDroplet) = this.x == other.x
    def isYCommon(other: LavaDroplet) = this.y == other.y
    def isZCommon(other: LavaDroplet) = this.z == other.z
  }

  def fromInput(input: Seq[String]): List[LavaDroplet] =
    input.toList
      .map {
        case s"$x,$y,$z" => LavaDroplet(x.toInt, y.toInt, z.toInt)
      }

  def updateSurfacesTouching(droplet1: LavaDroplet, droplet2: LavaDroplet): Sides = {
    val xCommon = droplet1.isXCommon(droplet2)
    val yCommon = droplet1.isYCommon(droplet2)
    val zCommon = droplet1.isZCommon(droplet2)

    (xCommon, yCommon, zCommon) match {
      case (true, true, false) => XY
      case (true, false, true) => ZX
      case (false, true, true) => YZ
    }
  }
}
