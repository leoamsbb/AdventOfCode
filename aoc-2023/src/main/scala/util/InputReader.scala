package util

import scala.io.Source

object InputReader {

  def readInput(filepath: String): Seq[String] = Source
    .fromResource(filepath)
    .getLines()
    .toSeq
}
