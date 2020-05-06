package com.example.inittrack2

import java.lang.Math.random

class Tile(var character: Character?, var content: Ground?, val x: Int, val y: Int) {
    // The following variables are used on the A* algorithm
    var parent: Tile? = null
    var gCost : Double = 0.0
    var hCost : Double = 0.0
    var randomNoise : Double = (0..2).random().toDouble()
    var fCost : Double = this.gCost + this.hCost + this.randomNoise
}

class TileComparator: Comparator<Tile>{
    override fun compare(o1: Tile?, o2: Tile?): Int {
        var x = when {
            (o1?.fCost == null || o2?.fCost == null) -> 0
            (o1.fCost > o2.fCost) -> -1
            (o1.fCost < o2.fCost) -> 1
            else -> 0
        }
        return(x)
    }
}