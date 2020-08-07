package com.github.caay2000.trains.world.`object`.entity

import com.github.caay2000.trains.world.`object`.location.Location
import kotlin.math.max
import kotlin.math.min

class Route {

    constructor(start: Location, next: Location, vararg stops: Location) {
        this.cities = mutableListOf(start, next, *stops)

        // TODO IMPROVE THIS
        for (x in cities) {
            for (y in cities) {
                x.addConnection(y)
                y.addConnection(x)
            }
        }
    }

    val cities: MutableList<Location>
    private var current = 0
    private var forward: Boolean = true

    fun addStop(stop: Location) {

        for (i in cities) {
            stop.addConnection(i)
            i.addConnection(stop)
        }
        this.cities.add(stop)
    }

    fun currentStop() = cities[current]

    fun nextStop() = cities[nextStopInt()]

    private fun isEndOfLine(i: Int = current): Boolean = i == 0 || i == cities.size - 1

    fun remainingStops(): Set<Location> {
        return if (forward) {
            cities.subList(current + 1, cities.size)
        } else {
            cities.subList(0, current)
        }.toSet()
    }

    fun update() {
        current = nextStopInt()
        if (isEndOfLine()) forward = !forward
    }

    private fun nextStopInt(value: Int = current): Int {
        val next = if (forward) value + 1 else value - 1
        return max(min(next, cities.size - 1), 0)
    }
}