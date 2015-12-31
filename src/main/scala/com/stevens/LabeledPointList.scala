package com.stevens

import scala.collection.mutable.MutableList

class LabeledPointList(pointDimension: Int) extends Queryable {
  var points: MutableList[LabeledPoint] = MutableList()
  val labeledPointDimension: Int = pointDimension

  def findNearestNeighbor(point: Array[Int]): LabeledPoint = {
    var nearest: LabeledPoint = null

    points.foreach { possibleNearest =>
      if (nearest == null) {
        nearest = possibleNearest
      } else {
        if (DistanceUtil.euclideanDistance(possibleNearest.vector, point) < DistanceUtil.euclideanDistance(nearest.vector, point)) {
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
}
