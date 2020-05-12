package com.github.caay2000.trains.world

import com.github.caay2000.trains.world.aaa.WorldEntity

class WorldSize(objects: Set<WorldEntity>) {
    private var maxX = 0
    private var maxY = 0
    var minX = 0
        private set
    var minY = 0
        private set

    private fun updateSize(obj: WorldEntity) {
        if (obj.position().x > maxX) {
            maxX = obj.position().x.toInt()
        }
        if (obj.position().x < minX) {
            minX = obj.position().x.toInt()
        }
        if (obj.position().y > maxY) {
            maxY = obj.position().y.toInt()
        }
        if (obj.position().y < minY) {
            minY = obj.position().y.toInt()
        }
    }

    init {
        for (obj in objects) {
            updateSize(obj)
        }
    }
}