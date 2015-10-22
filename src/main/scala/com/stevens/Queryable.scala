package com.stevens

trait Queryable {
  def findNearestNeighbor(point: Array[Int]): LabeledPoint
}
