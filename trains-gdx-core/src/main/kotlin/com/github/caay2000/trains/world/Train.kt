package com.github.caay2000.trains.world

import com.github.caay2000.trains.Configuration

class Train(private val speed: Float, private val route: Route) : Entity {

    override val position: Position = Position(route.start.position)
    private val onRoute = position != route.end.position

    private var stationDelta = 0f

    override fun update(delta: Float) {
        if (this.onRoute) {
            val rest = this.move(delta)
            if (rest > 0) {
                stationOperations(rest)
            }
        } else {
            stationOperations(delta)
        }
    }

    private fun stationOperations(delta: Float) {
        stationDelta += delta
        if (stationDelta >= Configuration.stationStopTime) {
            startTrain(stationDelta - stationDelta.toInt())
            stationDelta = 0f
        }
    }

    private fun startTrain(delta: Float) {
        this.route.endRoute()
        this.move(delta)
    }

    private fun move(delta: Float): Float {

        val missing = (this.speed * delta) - position.distanceTo(route.end.position)
        return if (missing <= 0) {
            position.move(route.end.position, speed * delta)
            0f
        } else {
            position.translate(route.end.position)
            delta * missing / (speed * delta)
        }
    }
}