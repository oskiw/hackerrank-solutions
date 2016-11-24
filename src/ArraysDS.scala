import scala.io.StdIn

/**
  * https://www.hackerrank.com/challenges/arrays-ds
  *
  * Created by oskiw on 24/11/2016.
  */
object ArraysDS {
  def main(args: Array[String]) {
    val n = StdIn.readInt

    println(
      StdIn
        .readLine
        .split(" ")
        .map(_.toInt)
        .take(n)
        .reverse
        .mkString(" ")
    )
  }
}
