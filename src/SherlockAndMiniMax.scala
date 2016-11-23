import scala.io.StdIn

/**
  * https://www.hackerrank.com/challenges/sherlock-and-minimax
  *
  * Created by oskiw on 21/11/2016.
  */
object SherlockAndMiniMax {

  def getMaxDistance(interval: (Int, Int), p: Int, q: Int) = {
    val candidate = (interval._1 + interval._2) / 2

    if (candidate < p)
      (interval._2 - p, p)
    else if (candidate > q)
      (q - interval._1, q)
    else
      (candidate - interval._1, candidate)
  }

  def max(x: (Int, Int), y: (Int, Int)) = {
    if (x._1 < y._1) y
    else x
  }

  def main(args: Array[String]) {
    val n = StdIn.readInt
    val a = StdIn.readLine.split(" ").map(_.toInt).take(n).sorted
    val Array(p, q) = StdIn.readLine.split(" ").map(_.toInt)

    val pDist = (a.head - p, p)
    val qDist = (q - a.reverse.head, q)

    println(
      if (a.length == 1)
        max(pDist, qDist)._2
      else
        max(
          a
            .sliding(2)
            .map{case Array(x, y) => (x, y)}
            .filterNot{case (x, y) => (x < p && y < p) || (x > q && y > q)}
            .map(getMaxDistance(_, p, q))
            .foldLeft(pDist)((acc, x) => max(acc, x)),
          qDist
        )._2
    )
  }
}
