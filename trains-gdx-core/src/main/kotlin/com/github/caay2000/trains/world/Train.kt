package com.github.caay2000.trains.world

import com.github.caay2000.trains.world.aaa.Position
import com.github.caay2000.trains.world.aaa.Route

class Train(var route: Route, speed: Double) {

    var position: Position
        private set
    val speed: Double

    fun move(elapsed: Float) {
        updatePosition(elapsed)
    }

    private fun updatePosition(elapsed: Float) {
        var elapsed = elapsed
        val distance = position.distanceTo(route.end.position())
        if (speed * elapsed > distance) {
            calculateNextRoute()
            elapsed = calculateElpsedMissing(elapsed, distance)
        }
        position.move(route.end.position(), (elapsed * speed).toFloat())
    }

    private fun calculateElpsedMissing(elapsed: Float, distance: Float): Float {
        var elapsed = elapsed
        var distance = distance
        distance = (elapsed * speed).toFloat() - distance
        elapsed = elapsed * distance / (speed * elapsed).toFloat()
        return elapsed
    }

    private fun calculateNextRoute() {
        position = Position(route.end.position())
        route = Route(route.end, route.start)
    }

    init {
        position = Position(route.start.position())
        this.speed = speed
    }
}