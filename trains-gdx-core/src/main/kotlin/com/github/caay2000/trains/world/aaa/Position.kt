package com.github.caay2000.trains.world.aaa

import com.badlogic.gdx.math.Vector2

class Position {

    private val vec2d: Vector2

    constructor(x: Int = 0, y: Int = 0) {
        vec2d = Vector2(x.toFloat(), y.toFloat())
    }

    constructor(x: Float, y: Float) {
        vec2d = Vector2(x, y)
    }

    constructor(position: Position) {
        vec2d = position.vec2d
    }

    private constructor(vec2d: Vector2) {
        this.vec2d = vec2d
    }

    private fun copy(): Position =
        Position(this.vec2d)

    val x: Float get() = this.vec2d.x
    val y: Float get() = this.vec2d.y

    fun distanceTo(to: Position): Float = this.vec2d.dst(to.vec2d)

    fun move(to: Position, distance: Float) {
        if (this.distanceTo(to) < distance) {
            var diff = this.copy().vec2d.sub(to.vec2d).nor()
            this.vec2d.mulAdd(diff.scl(-1f), distance)
        }
    }

    class Generator {

        private var maxDistance = 100
        private var minDistance = 100

        private var x = 0
        private var y = 0

        fun withCenter(center: Position): Generator {
            this.x = center.x.toInt()
            this.y = center.y.toInt()
            return this
        }

        fun withMaxDistance(maxDistance: Int): Generator {
            this.maxDistance = maxDistance
            return this
        }

        fun withMinDistance(minDistance: Int): Generator {
            this.minDistance = minDistance
            return this
        }

        fun generate(): Position = Position(
            ((x - maxDistance..x - minDistance) + (x + minDistance..x + maxDistance)).random(),
            ((y - maxDistance..y - minDistance) + (y + minDistance..y + maxDistance)).random()
        )
    }
}