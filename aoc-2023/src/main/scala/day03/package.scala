package object day03 {

  def splitInput(input: String): (String, String) =
    input.splitAt(input.length / 2)


  def misplacedItem(tuple: (String, String)): Char = {
    val (comp1, comp2) = tuple
    comp1.toCharArray.intersect(comp2.toCharArray).head
  }

  def badge(grouped: Seq[String]): Char = {
    grouped.map(_.toCharArray)
      .reduce((s1, s2) => s1.intersect(s2))
      .head
  }

  def priority(ch: Char): Int = ch match {
    case ch: Char if ch >= 'a' && ch <= 'z' => ch.toInt - 'a'.toInt + 1
    case ch: Char if ch >= 'A' && ch <= 'Z' => ch.toInt - 'A'.toInt + 27
  }

}
