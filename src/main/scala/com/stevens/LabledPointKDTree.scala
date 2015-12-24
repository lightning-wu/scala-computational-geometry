package com.stevens

import scala.util.Sorting

class LabeledPointKDTree(pointList: Array[LabeledPoint], pointDimension: Int) extends Queryable {
  val labeledPointDimension: Int = pointDimension
  val root: KDNode = divideNodeList(pointList, 0)

  def findNearestNeighbor(point: Array[Int]): LabeledPoint = {
    null
  }

  private def divideNodeList(pointList: Array[LabeledPoint], depth: Int): KDNode = {
    val axis = depth % pointDimension

    val ordering = Ordering.by[LabeledPoint, Int](_.vector(axis))

    Sorting.quickSort(pointList)(ordering)
    val median = pointList.length / 2
    val leftPoints = pointList.slice(0, median)
    val rightPoints = pointList.slice(median + 1, pointList.length + 1)

    val medianNode = new KDNode(axis, pointList(median).vector(axis), pointList(median))

    if (leftPoints.length > 0) {
      medianNode.leftChild = divideNodeList(leftPoints, depth + 1)
    }
    
    if (rightPoints.length > 0) {
      medianNode.rightChild = divideNodeList(rightPoints, depth + 1)
    }

    medianNode
  }
}
