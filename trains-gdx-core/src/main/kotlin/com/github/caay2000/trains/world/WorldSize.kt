package com.github.caay2000.trains.world

class WorldSize(objects: Set<WorldObject?>?) {
    private var maxX = 0
    private var maxY = 0
    var minX = 0
        private set
    var minY = 0
        private set

    private fun updateSize(obj: WorldObject?) {
        if (obj?.position?.x!! > maxX) {
            maxX = obj?.position?.x!!.toInt()
        }
        if (obj?.position?.x!! < minX) {
            minX = obj?.position?.x!!.toInt()
        }
        if (obj?.position?.y!! > maxY) {
            maxY = obj?.position?.y!!.toInt()
        }
        if (obj?.position?.y!! < minY) {
            minY = obj?.position?.y!!.toInt()
        }
    }

    init {
        for (obj in objects!!) {
            updateSize(obj)
        }
    }
}