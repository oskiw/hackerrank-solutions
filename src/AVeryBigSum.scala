import scala.io.StdIn

/**
  * https://www.hackerrank.com/challenges/a-very-big-sum
  *
  * Created by oskiw on 04/06/2016.
  */
object AVeryBigSum {
  def main(args: Array[String]): Unit = {
    val n = StdIn.readInt

    println(
      StdIn
        .readLine
        .split(" ")
        .take(n)
        .map(_.toLong)
        .sum
    )
  }
}
