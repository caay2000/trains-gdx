package com.github.caay2000.trains.world

class Train : Entity {

    override val position: Position
    private val speed: Float
    private var route: Route

    constructor(speed: Float, route: Route) {
        this.position = Position(route.start.position)
        this.speed = speed
        this.route = route
    }

    override fun update(delta: Float) {
        this.updatePosition(speed * delta)
    }

    private fun move(to: Location, distance: Float) {
    }

    private fun updatePosition(elapsed: Float) {
        var missing = elapsed
        val distance = position.distanceTo(route.end.position)
        if (speed * elapsed > distance) {
            calculateNextRoute()
            missing = calculateElapsedMissing(elapsed, distance)
        }
        position.move(route.end.position, missing * speed)
    }

    private fun calculateElapsedMissing(elapsed: Float, distance: Float): Float {
        val missing = (elapsed * speed) - distance
        return elapsed * missing / (speed * elapsed)
    }

    private fun calculateNextRoute() {
        position.translate(route.end.position)
        route = Route(route.end, route.start)
    }
}