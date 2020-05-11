package com.github.caay2000.trains.world

import com.github.caay2000.trains.common.Position
import com.github.caay2000.trains.util.RandomGenerator
import java.util.*

internal class WorldGenerator private constructor(private val configuration: WorldConfiguration?) {
    fun generateCities(configuration: WorldConfiguration?): Set<City> {
        val cities: MutableSet<City> = HashSet()
        for (i in 0 until WorldConfiguration.Companion.numberOfCities) {
            cities.add(generateNewCity(cities))
        }
        return cities
    }

    private fun generateNewCity(cities: Set<City>): City {
        val position = generateCityPosition(cities)
        val population = generateCityPopulation()
        return City(
                position.x,
                position.y,
                population)
    }

    private fun generateCityPopulation(): Int {
        return RandomGenerator.randomPositiveInt(WorldConfiguration.Companion.minPopulation, WorldConfiguration.Companion.maxPopulation)
    }

    private fun generateCityPosition(cities: Set<City>): Position {
        if (cities.size == 0) {
            return Position(0, 0)
        }
        val position = Position.Generator()
                .withCenter(getRandomCityPosition(cities))
                .withMaxDistance(WorldConfiguration.Companion.maxDistanceBetweenCities)
                .withMinDistance(WorldConfiguration.Companion.minDistanceBetweenCities)
                .generate()
        return if (validatePosition(position, cities)) {
//            System.out.println("valid position " + position);
            position
        } else generateCityPosition(cities)
        //        System.out.println("invalid position " + position);
    }

    private fun validatePosition(position: Position, cities: Set<City>): Boolean {
        for (city in cities) {
            if (position.distanceTo(city.position) < WorldConfiguration.Companion.minDistanceBetweenCities) {
                return false
            }
        }
        return true
    }

    private fun getRandomCityPosition(cities: Set<City>): Position {
        return RandomGenerator.randomItem(cities).position

    }

    companion object {
        fun aWorldGenerator(configuration: WorldConfiguration?): WorldGenerator {
            return WorldGenerator(configuration)
        }
    }

}