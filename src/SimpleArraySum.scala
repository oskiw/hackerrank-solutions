import scala.io.StdIn

/**
  * https://www.hackerrank.com/challenges/simple-array-sum
  *
  * Created by oskiw on 04/06/2016.
  */
object SimpleArraySum {
  def main(args: Array[String]): Unit = {
    val n = StdIn.readInt

    println(
      StdIn
        .readLine
        .split(" ")
        .map(_.toInt)
        .take(n)
        .sum
    )
  }
}
