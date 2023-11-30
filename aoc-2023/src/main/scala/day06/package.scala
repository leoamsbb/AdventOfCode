package object day06 {

  def findMarker(input: String, numOfUniqueChars: Int) : Int = {
    val foundMarker = input.sliding(numOfUniqueChars)
      .indexWhere (_.toCharArray.toSet.size == numOfUniqueChars)
    numOfUniqueChars + foundMarker
  }
}
