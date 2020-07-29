package com.github.caay2000.trains.world.position

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
        vec2d = Vector2(position.x, position.y)
    }

    private constructor(vec2d: Vector2) {
        this.vec2d = Vector2(vec2d.x, vec2d.y)
    }

    private fun copy(): Position =
        Position(this.vec2d)

    val x: Float get() = this.vec2d.x
    val y: Float get() = this.vec2d.y

    fun distanceTo(to: Position): Float = this.vec2d.dst(to.vec2d)

    fun move(to: Position, distance: Float) {
        if (distance <= this.distanceTo(to)) {
            val diff = this.copy().vec2d.sub(to.vec2d).nor()
            this.vec2d.mulAdd(diff.scl(-1f), distance)
        } else this.translate(to)
    }

    fun translate(position: Position) {
        this.vec2d.set(position.x, position.y)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Position
        return vec2d.x == other.vec2d.x && vec2d.y == other.vec2d.y
    }

    override fun hashCode(): Int {
        return vec2d.hashCode()
    }

    override fun toString(): String {
        return "Position(x=${vec2d.x},y=${vec2d.y})"
    }
}