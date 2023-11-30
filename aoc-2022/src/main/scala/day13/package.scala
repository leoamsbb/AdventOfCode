import scala.annotation.tailrec

package object day13 {

  trait NestedIntElem {
    def isInt: Boolean

    def getInt: Int

    def isList: Boolean

    def getList: List[NestedIntElem]
  }

  case class NestedInt(number: Int) extends NestedIntElem {
    override def isInt: Boolean = true

    override def getInt: Int = number

    override def getList: List[NestedIntElem] = throw new IllegalAccessException("getList is not applicable here")

    override def isList: Boolean = false
  }

  case class NestedList(list: List[NestedIntElem]) extends NestedIntElem {
    override def isInt: Boolean = false

    override def getInt: Int = throw new IllegalAccessException("getInt is not applicable here")

    override def getList: List[NestedIntElem] = list

    override def isList: Boolean = true
  }

  def fromInput(input: Seq[String]) = {
    val nonBlanks = input.filterNot(_.isBlank)
    nonBlanks.map(fromLine)
  }

  def getSublist(line: String): String = {
    var stack = List.empty[Char]
    var index = 0
    var indexWhereBracketEnds = -1
    while(stack.nonEmpty) {
      line(index) match {
        case '[' => stack = '[' :: stack
        case ']' =>
          stack = stack.tail
          indexWhereBracketEnds = index
      }
      index += 1
    }
    line.slice(0, indexWhereBracketEnds)
  }

  def fromLine(line: String): List[NestedIntElem] = {
    println(s"line: $line")
    var result = List.empty[NestedIntElem]
    var stack = List(line.slice(1, line.length - 1))

    if (line == "[[10],[4,3,5,[[],8,[4,3,10,9,4]],5],[[[6],[8]],9,1],[4,8,2,[[],[2,1,7]],6]]") {
      while (stack.nonEmpty) {
        val popped = stack.head
        popped match {
          case str if str.startsWith("[") =>
            val sublist = getSublist(str)
            result = result ::: fromLine(sublist)
          case s"[$nested],$rest" =>
            stack = stack.tail
            stack = rest :: stack
            if (!nested.contains('[')) {
              result = result :+ NestedList(nested.split(",").map(_.toInt).map(NestedInt).toList)
            } else {
              stack = nested :: stack
            }
          case s"[$nested]" if nested.nonEmpty =>
            stack = stack.tail
            if (nested.startsWith("[")) {
              result = result :+ NestedList(fromLine(nested))
            } else if (!nested.contains('[')) {
              result = result :+ NestedList(nested.split(",").map(_.toInt).map(NestedInt).toList)
            } else {
              stack = nested :: stack
            }
          case s"$simple,$rest" =>
            stack = rest :: stack.tail
            result = result :+ NestedInt(simple.toInt)
          case "[]" | "" =>
            stack = stack.tail
            result = result :+ NestedList(List.empty[NestedIntElem])
          case s"$onlyNumber" if onlyNumber.nonEmpty =>
            stack = stack.tail
            result = result :+ NestedInt(onlyNumber.toInt)
        }
      }
    } else {
      while (stack.nonEmpty) {
        val popped = stack.head
        popped match {
          case s"[$nested],$rest" =>
            stack = stack.tail
            stack = rest :: stack
            if (!nested.contains('[')) {
              result = result :+ NestedList(nested.split(",").map(_.toInt).map(NestedInt).toList)
            } else {
              stack = nested :: stack
            }
          case s"[$nested]" if nested.nonEmpty =>
            stack = stack.tail
            if (nested.startsWith("[")) {
              result = result :+ NestedList(fromLine(nested))
            } else if (!nested.contains('[')) {
              result = result :+ NestedList(nested.split(",").map(_.toInt).map(NestedInt).toList)
            } else {
              stack = nested :: stack
            }
          case s"$simple,$rest" =>
            stack = rest :: stack.tail
            result = result :+ NestedInt(simple.toInt)
          case "[]" | "" =>
            stack = stack.tail
            result = result :+ NestedList(List.empty[NestedIntElem])
          case s"$onlyNumber" if onlyNumber.nonEmpty =>
            stack = stack.tail
            result = result :+ NestedInt(onlyNumber.toInt)
        }
      }
    }

    result
  }

  def getListFromCommaSeparated(str: String): NestedList =
    NestedList(str.split(",").map(_.toInt).map(NestedInt).toList)

  def fromLineRec(line: String) = {

    def loop(acc: List[NestedIntElem], input: String): List[NestedIntElem] = {
      if (input.isEmpty)
        acc
      else
        input match {
          case s"[$nested],$rest" =>
            var append = Option.empty[NestedIntElem]
            if (!nested.contains('[')) {
              append = Option(getListFromCommaSeparated(nested))
            } else {
              loop(acc, nested)
            }
            val appended = append.map(list => acc :+ list).getOrElse(acc)
            loop(appended, rest)
          case s"[$nested]" if nested.nonEmpty =>
            if (nested.startsWith("[")) {
              loop(acc :+ loop(Nil, nested).head, "")
            } else if (!nested.contains('[')) {
              loop(acc :+ getListFromCommaSeparated(nested), "")
            } else {
              loop(acc, nested)
            }
          case s"$simple,$rest" =>
            loop(acc :+ NestedInt(simple.toInt), rest)
          case "[]" | "" =>
            loop(acc :+ NestedList(List.empty[NestedIntElem]), "")
          case s"$onlyNumber" if onlyNumber.nonEmpty =>
            loop(acc :+ NestedInt(onlyNumber.toInt), "")
        }
    }

    loop(Nil, line.slice(1, line.length - 1))
  }

  def compare(list1: List[NestedIntElem], list2: List[NestedIntElem]): Boolean = {

    @tailrec
    def loop(h1: Option[NestedIntElem], h2: Option[NestedIntElem], t1: List[NestedIntElem], t2: List[NestedIntElem]): Boolean = {
      if (h1.isEmpty && h2.nonEmpty)
        true
      else if (h1.nonEmpty && h2.isEmpty)
        false
      else if (h1.isEmpty && h2.isEmpty)
        true
      else {
        val elem1 = h1.get
        val elem2 = h2.get
        if (elem1.isInt && elem2.isInt) {
          if (elem1.getInt > elem2.getInt) {
            false
          } else {
            loop(t1.headOption, t2.headOption, getTail(t1), getTail(t2))
          }
        } else if (elem1.isList && elem2.isList) {
          loop(elem1.getList.headOption, elem2.getList.headOption, getTail(elem1), getTail(elem2))
        } else {
          if (elem1.isInt && elem2.isList) {
            compareElemWithList(elem1.getInt, elem2.getList)
          } else {
            elem1.getList.forall(e => compareElemWithList(e.getInt, elem2.getList))
          }
        }
      }
    }

    loop(list1.headOption, list2.headOption, getTail(list1), getTail(list2))
  }

  private def getTail(list: NestedIntElem): List[NestedIntElem] = {
    val extracted = list.getList
    if (extracted.isEmpty) Nil else extracted.tail
  }

  private def getTail(list: List[NestedIntElem]): List[NestedIntElem] = {
    if (list.isEmpty) Nil else list.tail
  }

  private def compareElemWithList(elem: Int, list: List[NestedIntElem]): Boolean = {

    @tailrec
    def loop(head: Option[NestedIntElem], tail: List[NestedIntElem]): Boolean = {
      if (head.isEmpty)
        true
      else {
        val headElem = head.get
        if (headElem.isInt && elem > headElem.getInt)
          false
        else if (headElem.isList)
          loop(headElem.getList.headOption, headElem.getList.tail)
        else
          loop(tail.headOption, tail.tail)
      }
    }

    loop(list.headOption, list.tail)
  }
}
