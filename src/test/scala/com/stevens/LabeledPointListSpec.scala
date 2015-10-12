package com.stevens

import org.scalatest._

class LabeledPointListSpec extends FlatSpec with Matchers {

  "The nearest-neighbor to the origin" should "be (1,1)" in {
    val pointList = new LabeledPointList(2)
    pointList.addPoint(new LabeledPoint("closest", Array(1.0f, 1.0f)))
    pointList.addPoint(new LabeledPoint("bad point 1", Array(1.0f, 2.0f)))
    pointList.addPoint(new LabeledPoint("bad point 2", Array(-5.0f, 3.0f)))
    pointList.addPoint(new LabeledPoint("bad poitn 3", Array(3.1f, 1.0f)))

    val nearestPoint = pointList.findNearestNeighbor(Array(0.0f,0.0f))
    nearestPoint.name should be ("closest")
    nearestPoint.vector should be (Array(1.0f, 1.0f))
  }
}
