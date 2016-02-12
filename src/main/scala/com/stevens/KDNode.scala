package com.stevens

class KDNode(axs: Int = 0, valu: Int = 0, pnt: LabeledPoint = null) {
	val axis = axs
	val value = valu
	val point = pnt
	var leftChild: KDNode = null
	var rightChild: KDNode = null
}