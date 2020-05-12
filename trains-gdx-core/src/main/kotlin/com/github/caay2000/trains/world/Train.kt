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
        var elapsed = elapsed
        val distance = position.distanceTo(route.end.position)
        if (speed * elapsed > distance) {
            calculateNextRoute()
            elapsed = calculateElapsedMissing(elapsed, distance)
        }
        position.move(route.end.position, elapsed * speed)
    }

    private fun calculateElapsedMissing(elapsed: Float, distance: Float): Float {
        var elapsed = elapsed
        var distance = distance
        distance = (elapsed * speed) - distance
        elapsed = elapsed * distance / (speed * elapsed)
        return elapsed
    }

    private fun calculateNextRoute() {
        position.translate(route.end.position)
        route = Route(route.end, route.start)
    }
}