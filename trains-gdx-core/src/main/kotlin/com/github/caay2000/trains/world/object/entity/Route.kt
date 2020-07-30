package com.github.caay2000.trains.world.`object`.entity

import com.github.caay2000.trains.world.`object`.location.Location

class Route(start: Location, next: Location, vararg stops: Location) {

    val cities = mutableListOf(start, next, *stops)
    private var current = 0

    fun addStop(stop: Location) {
        this.cities.add(stop)
    }

    fun currentStop() = cities[current]

    fun nextStop() = cities[nextStopInt()]

    // TO IMPROVE
    fun remainingStops(): Set<Location> {
        val set = mutableSetOf<Location>()
        var i = nextStopInt()
        while (i != current) {
            if (cities[i] == currentStop()) {
                return set
            }
            set.add(cities[i])
            i = nextStopInt(i)
        }
        return set
    }

    fun update() {
        current = nextStopInt()
    }

    private fun nextStopInt(value: Int = current): Int {
        var next = value + 1
        if (next >= cities.size) {
            next = 0
        }
        return next
    }
}