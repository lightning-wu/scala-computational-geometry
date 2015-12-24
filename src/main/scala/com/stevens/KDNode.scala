package com.stevens

class KDNode(axs: Int, valu: Int, pnt: LabeledPoint) {
	val axis = axs
	val value = valu
	val point = pnt
	var leftChild: KDNode = null
	var rightChild: KDNode = null
}