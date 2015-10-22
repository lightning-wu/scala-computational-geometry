package com.stevens

import org.scalatest._

class LabeledPointListSpec extends FlatSpec with Matchers {

  "The nearest-neighbor to (1,1)" should "be (2,3)" in {
    val pointList = new LabeledPointList(2)
    pointList.addPoint(new LabeledPoint("closest", Array(2, 3)))
    pointList.addPoint(new LabeledPoint("bad point 1", Array(10, 2)))
    pointList.addPoint(new LabeledPoint("bad point 2", Array(-5, 3)))
    pointList.addPoint(new LabeledPoint("bad point 3", Array(3, 10)))

    val nearestPoint = pointList.findNearestNeighbor(Array(1, 1))
    nearestPoint.name should be ("closest")
    nearestPoint.vector should be (Array(2, 3))
  }
}
