package com.stevens

import scala.util.Sorting

class LabeledPointKDTree(pointList: Array[LabeledPoint], pointDimension: Int) extends Queryable {
  val labeledPointDimension: Int = pointDimension
  val root: KDNode = divideNodeList(pointList, 0)

  def findNearestNeighbor(point: Array[Int]): LabeledPoint = {
    val nearestNode = nearestNeighborSearch(point, root, root, Double.PositiveInfinity)
    return nearestNode.point
  }

  private def nearestNeighborSearch(queryPoint: Array[Int], currentNode: KDNode, candidatePoint: KDNode, bestDistance: Double): KDNode = {
    var currentCandidatePoint = candidatePoint

    if (currentNode.leftChild == null && currentNode.rightChild == null) {
      if (currentNode.point.vector.deep != queryPoint.deep) {
        currentCandidatePoint = currentNode
      }

      return currentCandidatePoint
    }

    var currentBestDistance = bestDistance
    val axisValue = queryPoint(currentNode.axis)

    if (axisValue <= currentNode.value) {
      if (axisValue - currentBestDistance <= currentNode.value) {
        currentCandidatePoint = nearestNeighborSearch(queryPoint, currentNode.leftChild, currentCandidatePoint, currentBestDistance)
        if (currentCandidatePoint != null) {
          currentBestDistance = DistanceUtil.euclideanDistance(queryPoint, currentCandidatePoint.point.vector)
        }
      }

      if (axisValue + currentBestDistance > currentCandidatePoint.value) {
        currentCandidatePoint = nearestNeighborSearch(queryPoint, currentNode.rightChild, currentCandidatePoint, currentBestDistance)
        if (currentCandidatePoint != null) {
          currentBestDistance = DistanceUtil.euclideanDistance(queryPoint, currentCandidatePoint.point.vector)
        }
      }
    } else {
      if (axisValue + currentBestDistance <= currentNode.value) {
        currentCandidatePoint = nearestNeighborSearch(queryPoint, currentNode.rightChild, currentCandidatePoint, currentBestDistance)
        if (currentCandidatePoint != null) {
          currentBestDistance = DistanceUtil.euclideanDistance(queryPoint, currentCandidatePoint.point.vector)
        }
      }

      if (axisValue - currentBestDistance > currentCandidatePoint.value) {
        currentCandidatePoint = nearestNeighborSearch(queryPoint, currentNode.leftChild, currentCandidatePoint, currentBestDistance)
        if (currentCandidatePoint != null) {
          currentBestDistance = DistanceUtil.euclideanDistance(queryPoint, currentCandidatePoint.point.vector)
        }
      }
    }

    return currentCandidatePoint
  }

  private def divideNodeList(pointList: Array[LabeledPoint], depth: Int): KDNode = {
    if (pointList.length <= 0) {
      return null
    }

    val axis = depth % pointDimension

    if (pointList.length <= 1) {
      val point = pointList(0)
      val node = new KDNode(axis, point.vector(axis), point)

      return node
    }

    if (pointList.length <= 2) {
      if (pointList(0).vector(axis) <= pointList(1).vector(axis)) {
        val parentNode = new KDNode(axis, pointList(0).vector(axis), pointList(0))

        parentNode.leftChild = new KDNode(pnt = pointList(0))
        parentNode.rightChild = new KDNode(pnt = pointList(1))

        return parentNode
      }
      else {
        val parentNode = new KDNode(axis, pointList(1).vector(axis), pointList(1))

        parentNode.leftChild = new KDNode(pnt = pointList(1))
        parentNode.rightChild = new KDNode(pnt = pointList(0))

        return parentNode
      }
    }

    val ordering = Ordering.by[LabeledPoint, Int](_.vector(axis))

    Sorting.quickSort(pointList)(ordering)
    val median = pointList.length / 2
    val leftPoints = pointList.slice(0, median + 1)
    val rightPoints = pointList.slice(median + 1, pointList.length + 1)

    val medianNode = new KDNode(axis, pointList(median).vector(axis))
    medianNode.leftChild = divideNodeList(leftPoints, depth + 1)
    medianNode.rightChild = divideNodeList(rightPoints, depth + 1)
    
    medianNode
  }
}
