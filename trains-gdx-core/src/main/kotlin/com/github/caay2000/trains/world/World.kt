package com.github.caay2000.trains.world

import com.github.caay2000.trains.Configuration
import com.github.caay2000.trains.world.`object`.location.AbstractLocation
import com.github.caay2000.trains.world.`object`.location.city.City
import com.github.caay2000.trains.world.company.Company
import kotlin.math.roundToInt

class World {

    val locations: Set<AbstractLocation>
    val companies: Set<Company>

    private val size: WorldSize

    private constructor(cities: Set<AbstractLocation>, size: WorldSize) {
        this.locations = cities
        this.companies = mutableSetOf()
        this.size = size
    }

    fun locations() = this.locations.toSet()
    fun cities() = this.locations.filterIsInstance<City>().toSet()
    fun companies() = this.companies

    fun minX() = this.size.minX
    fun minY() = this.size.minY

    fun addCompany(company: Company) = (this.companies as MutableSet).add(company)

    fun update() {
        this.companies.forEach { it.update(GlobalData.delta) }
        this.locations.forEach { it.update(GlobalData.delta) }
    }

    class Builder {

        private val cities: MutableSet<AbstractLocation> = mutableSetOf()
        private val size: WorldSize =
            WorldSize()

        fun addLocation(Location: AbstractLocation) {
            this.cities.add(Location)
            this.size.update(Location)
            this.updateConnections(Location)
        }

        private fun updateConnections(newLocation: AbstractLocation) {
            for (existingLocation in cities) {
                if (newLocation.distanceTo(existingLocation) < Configuration.maxRouteDistanceBetweenCities && newLocation != existingLocation) {
                    newLocation.addLocationInRange(existingLocation)
                    existingLocation.addLocationInRange(newLocation)
                }
            }
        }

        fun cities() = this.cities

        fun build(): World = World(cities, size)
    }

    private class WorldSize {
        var minX: Int = 0
        var minY: Int = 0
        var maxX: Int = 0
        var maxY: Int = 0

        fun update(Location: AbstractLocation) {
            if (Location.position.x < minX) {
                minX = Location.position.x.roundToInt()
            }
            if (Location.position.y < minY) {
                minY = Location.position.y.roundToInt()
            }
            if (Location.position.x > maxX) {
                maxX = Location.position.x.roundToInt()
            }
            if (Location.position.x > maxY) {
                maxY = Location.position.y.roundToInt()
            }
        }
    }
}

