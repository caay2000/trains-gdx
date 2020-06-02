package com.github.caay2000.trains.world

import com.github.caay2000.trains.Configuration
import com.github.caay2000.trains.world.generator.StubSimulation
import com.github.caay2000.trains.world.location.City
import kotlin.math.roundToInt

class World {

    val cities: Set<City>
    val companies: Set<Company>

    private val size: WorldSize

    private constructor(cities: Set<City>, size: WorldSize) {
        this.cities = cities
        this.companies = mutableSetOf()
        this.size = size
    }

    fun cities() = this.cities.filterIsInstance<City>().toSet()
    fun companies() = this.companies

    fun minX() = this.size.minX
    fun minY() = this.size.minY

    fun addCompany(company: Company) = (this.companies as MutableSet).add(company)

    fun update() {
        this.companies.forEach { it.update(GlobalData.delta) }
        this.cities.forEach { it.update(GlobalData.delta) }
        testUpdate()
    }

    private fun testUpdate() {
        StubSimulation.stubSimulationUpdate(this)
    }

    class Builder {

        private val cities: MutableSet<City> = mutableSetOf()
        private val size: WorldSize =
            WorldSize()

        fun addCity(City: City) {
            this.cities.add(City)
            this.size.update(City)
            this.updateConnections(City)
        }

        private fun updateConnections(newCity: City) {
            for (existingCity in cities) {
                if (newCity.distanceTo(existingCity) < Configuration.maxRouteDistanceBetweenCities && newCity != existingCity) {
                    newCity.addCityInRange(existingCity)
                    existingCity.addCityInRange(newCity)
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

        fun update(City: City) {
            if (City.position.x < minX) {
                minX = City.position.x.roundToInt()
            }
            if (City.position.y < minY) {
                minY = City.position.y.roundToInt()
            }
            if (City.position.x > maxX) {
                maxX = City.position.x.roundToInt()
            }
            if (City.position.x > maxY) {
                maxY = City.position.y.roundToInt()
            }
        }
    }
}

