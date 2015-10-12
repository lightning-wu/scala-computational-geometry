package com.stevens

import scala.collection.mutable.MutableList

class LabeledPointList(pointDimension: Int) extends Queryable {
  var points: MutableList[LabeledPoint] = MutableList()
  val labeledPointDimension: Int = pointDimension

  def findNearestNeighbor(point: Array[Float]): LabeledPoint = {
    var nearest: LabeledPoint = null

    points.foreach { possibleNearest =>
      if (nearest == null) {
        nearest = possibleNearest
      } else {
        if (cosine(possibleNearest.vector, point) < cosine(nearest.vector, point)) {
          nearest = possibleNearest
        }
      }
    }

    nearest
  }

  def addPoint(point: LabeledPoint) {
    if (point.vector.length != labeledPointDimension) {
      throw new IllegalArgumentException("Passed point dimensionality " +
        "does not match degree specified for this list")
    }

    points += point
  }

  private def cosine(point1: Array[Float], point2: Array[Float]): Float = {
    val dotProduct = point1.zip(point2).map {case(val1, val2) =>
      val1 * val2
    }.sum

    val normalized1 = Math.sqrt(point1.zip(point1).map {case(val1, val2) =>
      val1 * val2
    }.sum)

    val normalized2 = Math.sqrt(point2.zip(point2).map {case(val1, val2) =>
      val1 * val2
    }.sum)

    dotProduct / (normalized1.toFloat * normalized1.toFloat)
  }
}
