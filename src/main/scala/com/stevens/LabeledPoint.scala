package com.stevens

class LabeledPoint(label: String, coordinate: Array[Int]) {
  val name: String = label
  val vector: Array[Int] = coordinate

  override def toString: String = name + " [" + vector.mkString(",") + "]"
}
