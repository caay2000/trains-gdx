package com.github.caay2000.trains.world

import com.github.caay2000.trains.common.Position
import java.util.*

class City(x: Float, y: Float, population: Int) : WorldObject {

    override val position: Position
    val population: Int
    var isConnected: Boolean

    private var citiesInRange: HashSet<City?>? = null

    val isNotConnected: Boolean
        get() = !isConnected

    override fun distanceTo(to: WorldObject): Float {
        return position.distanceTo(to.position)
    }

    fun getCitiesInRange(cities: Set<City?>?): Set<City?> {
        if (citiesInRange == null) {
            citiesInRange = HashSet()
            for (city in cities!!) {
                if (city!!.distanceTo(this) <= WorldConfiguration.Companion.maxRouteDistance) {
                    citiesInRange!!.add(city)
                }
            }
        }
        return citiesInRange!!
    }

    fun connected() {
        isConnected = true
    }

    init {
        position = Position(x, y)
        this.population = population
        isConnected = false
    }
}