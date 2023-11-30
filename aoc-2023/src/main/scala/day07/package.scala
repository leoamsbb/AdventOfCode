package object day07 {

  case class File(size: Long, name: String)

  class Dir(val path: String) {
    var files : List[File] = Nil
    var dirs: List[Dir] = Nil

    def addFile(file: File): Unit = files = file :: files

    def addDir(dir: Dir): Unit = dirs = dir :: dirs

    def size : Long = files.map(_.size).sum + dirs.map(_.size).sum
  }

  def listOfDirs(input: Seq[String]): List[Dir] = {
    val root = new Dir("/")
    var formattedInput: List[Dir] = List(root)

    def getDir(path: String) = {
      val dir = formattedInput.find(_.path == path)
      dir.getOrElse {
        val newDir = new Dir(path)
        formattedInput = newDir :: formattedInput
        newDir
      }
    }

    var currentDir = root

    input.foreach {
      case "$ cd /" => currentDir = root
      case "$ cd .." =>
        val currentDirPath = currentDir.path
        currentDir = getDir(currentDirPath.slice(0, currentDirPath.lastIndexOf("/")))
      case s"$number $string" if number.forall(_.isDigit) => currentDir.addFile(File(number.toLong, string))
      case s"$$ cd $dir" =>
        val newDir = getDir(s"${currentDir.path}/$dir")
        currentDir.addDir(newDir)
        currentDir = newDir
      case _ => ()
    }

    formattedInput
  }
}
