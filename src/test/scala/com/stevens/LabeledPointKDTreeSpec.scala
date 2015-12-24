package com.stevens

import org.scalatest._

class LabeledPointKDTreeSpec extends FlatSpec with Matchers {

	"Building a KD Tree" should "work without error" in {
		val pointList = Array(new LabeledPoint("closest", Array(2, 3)),
    					  	  new LabeledPoint("bad point 1", Array(10, 2)),
    					  	  new LabeledPoint("bad point 2", Array(-5, 3)),
    					  	  new LabeledPoint("bad point 3", Array(3, 10)))

		val kdTree = new LabeledPointKDTree(pointList, 2)

		kdTree.root should not be null
	}
}