package com.stevens

trait Queryable {
  def findNearestNeighbor(point: Array[Float]): LabeledPoint
}
