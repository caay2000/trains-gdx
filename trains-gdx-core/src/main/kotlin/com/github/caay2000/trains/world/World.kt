package com.github.caay2000.trains.world

import com.github.caay2000.trains.Configuration
import kotlin.math.roundToInt

class World {

    private val locations: Set<Location>
    private val size: WorldSize

    private val companies: MutableSet<Company>

    private constructor(locations: Set<Location>, size: WorldSize) {
        this.locations = locations
        this.companies = mutableSetOf()
        this.size = size
    }

    fun cities() = this.locations.filterIsInstance<City>().toSet()
    fun companies() = this.companies

    fun minX() = this.size.minX
    fun minY() = this.size.minY

    fun addCompany(company: Company) = this.companies.add(company)

    fun update(delta: Float) {
        this.companies.forEach { it.update(delta) }
        this.locations.forEach { it.update(delta) }
        testUpdate()
    }

    private fun testUpdate() {
        if (companies.isEmpty()) {
            companies.add(Company())
        }

        try {
            val start = if (locations.filter { e -> e.connected() }.none()) locations.random()
            else locations.filter { e -> e.connected() }.random()

            val end = start.locationsInRange.filter { e -> !e.connected() }.random()
            val route = Route(start, end)
            companies.random().addRoute(route)
            start.addConnection(end)
            end.addConnection(start)
            companies.random().addEntity(Train((5..10).random().toFloat(), route))
        } catch (e: Exception) {

        }
    }

    class Builder {

        private val locations: MutableSet<Location> = mutableSetOf()
        private val size: WorldSize =
            WorldSize()

        fun addLocation(location: Location) {
            this.locations.add(location)
            this.size.update(location)
            this.updateConnections(location)
        }

        private fun updateConnections(newLocation: Location) {
            for (existingLocation in locations) {
                if (newLocation.distanceTo(existingLocation) < Configuration.maxRouteDistanceBetweenCities) {
                    newLocation.addLocationInRange(existingLocation)
                    existingLocation.addLocationInRange(newLocation)
                }
            }
        }

        fun locations() = this.locations

        fun build(): World = World(locations, size)
    }

    private class WorldSize {
        var minX: Int = 0
        var minY: Int = 0
        var maxX: Int = 0
        var maxY: Int = 0

        fun update(location: Location) {
            if (location.position.x < minX) {
                minX = location.position.x.roundToInt()
            }
            if (location.position.y < minY) {
                minY = location.position.y.roundToInt()
            }
            if (location.position.x > maxX) {
                maxX = location.position.x.roundToInt()
            }
            if (location.position.x > maxY) {
                maxY = location.position.y.roundToInt()
            }
        }
    }
}

