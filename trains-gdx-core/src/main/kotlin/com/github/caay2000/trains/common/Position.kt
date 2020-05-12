// package com.github.caay2000.trains.common
//
// import com.badlogic.gdx.math.Vector2
// import com.github.caay2000.trains.util.RandomGenerator
// import java.util.*
//
// class Position {
//     protected val vec2d: Vector2
//
//     constructor() {
//         vec2d = Vector2.Zero
//     }
//
//     constructor(position: Position) {
//         vec2d = position.vec2d
//     }
//
//     protected constructor(vec2d: Vector2) {
//         this.vec2d = vec2d
//     }
//
//     constructor(x: Int, y: Int) {
//         vec2d = Vector2(x.toFloat(), y.toFloat())
//     }
//
//     constructor(x: Float, y: Float) {
//         vec2d = Vector2(x, y)
//     }
//
//     val x: Float
//         get() = vec2d.x
//
//     val y: Float
//         get() = vec2d.y
//
//     fun distanceTo(to: Position): Float {
//         return vec2d.dst(to.vec2d)
//     }
//
//     protected fun copy(): Position {
//         return Position(vec2d.x, vec2d.y)
//     }
//
//     fun move(to: Position, distance: Float): Position {
//         if (distance > vec2d.dst(to.vec2d)) {
//             return to.copy()
//         }
//         var diff = copy().vec2d.sub(to.vec2d).nor()
//         diff = Vector2(diff.x * -1, diff.y * -1)
//         return Position(copy().vec2d.mulAdd(diff, distance))
//     }
//
//     override fun toString(): String {
//         return StringJoiner(", ", Position::class.java.simpleName + "[", "]")
//                 .add("x=" + vec2d.x)
//                 .add("y=" + vec2d.y)
//                 .toString()
//     }
//
//     override fun equals(o: Any?): Boolean {
//         if (this === o) return true
//         if (o == null || javaClass != o.javaClass) return false
//         val position = o as Position
//         return vec2d == position.vec2d
//     }
//
//     override fun hashCode(): Int {
//         return Objects.hash(vec2d)
//     }
//
//     class Generator {
//         private var center = Position()
//         private var maxDistance = 100
//         private var minDistance = 100
//         fun withCenter(center: Position): Generator {
//             this.center = center
//             return this
//         }
//
//         fun withMaxDistance(maxDistance: Int): Generator {
//             this.maxDistance = maxDistance
//             return this
//         }
//
//         fun withMinDistance(minDistance: Int): Generator {
//             this.minDistance = minDistance
//             return this
//         }
//
//         fun generate(): Position {
//             val rangeX = center.x.toInt() + maxDistance
//             val rangeY = center.y.toInt() + maxDistance
//             val x = RandomGenerator.randomSignedInt(Math.negateExact(rangeX), rangeX)
//             val y = RandomGenerator.randomSignedInt(Math.negateExact(rangeY), rangeY)
//             val newPosition = Position(x, y)
//             return if (newPosition.distanceTo(center) > minDistance) {
//                 newPosition
//             } else generate()
//         }
//     }
// }