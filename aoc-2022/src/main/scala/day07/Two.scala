package day07

object Two {

  def run(input: Seq[String]): Long = {
    val directories = listOfDirs(input)

    val totalDiskSize = 70000000L
    val requiredUnusedDisk = 30000000L

    val diskUsedByOutermostDir = directories.find(_.path == "/").get.size
    val unusedDiskRightNow = totalDiskSize - diskUsedByOutermostDir

    val toBeFreed = requiredUnusedDisk - unusedDiskRightNow

    directories.map(_.size).sorted.find(_ >= toBeFreed).get
  }
}
