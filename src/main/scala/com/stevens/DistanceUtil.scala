package com.stevens

object DistanceUtil {
  def squaredDistance(point1: Array[Int], point2: Array[Int]): Double = {
    if (point1.length != point2.length) {
      throw new IllegalArgumentException("Vector sizes do not match")
    }

    point1.zip(point2).map {case(val1, val2) =>
      Math.pow(val1 * val2, 2)
    }.sum
  }

  def euclideanDistance(point1: Array[Int], point2: Array[Int]): Double = {
    Math.sqrt(squaredDistance(point1, point2))
  }

  def cosineSimilarity(point1: Array[Int], point2: Array[Int]): Double = {
    val dotProduct = point1.zip(point2).map {case(val1, val2) =>
      val1 * val2
    }.sum

    val normalized1 = Math.sqrt(point1.zip(point1).map {case(val1, val2) =>
      val1 * val2
    }.sum)

    val normalized2 = Math.sqrt(point2.zip(point2).map {case(val1, val2) =>
      val1 * val2
    }.sum)

    dotProduct.toDouble / (normalized1.toDouble * normalized1.toDouble)
  }
}
