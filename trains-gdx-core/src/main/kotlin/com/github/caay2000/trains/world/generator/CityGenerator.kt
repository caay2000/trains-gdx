package com.github.caay2000.trains.world.generator

import com.github.caay2000.trains.Configuration
import com.github.caay2000.trains.world.City
import com.github.caay2000.trains.world.Location
import com.github.caay2000.trains.world.Position

object CityGenerator {

    fun generateCity(existingLocations: Set<Location>, configuration: Configuration = Configuration): City {
        val position = randomPosition(existingLocations, configuration)
        if (position.isInvalid(existingLocations)) {
            return generateCity(existingLocations, configuration)
        }
        val name = NameGenerator.generate()
        return City(name, position, randomPopulation())
    }

    private fun randomPosition(
        existingLocations: Set<Location>,
        configuration: Configuration
    ): Position {

        return if (existingLocations.isEmpty()) Position()
        else PositionGenerator
            .withCenter(existingLocations.random().position)
            .withMaxDistance(configuration.maxDistanceBetweenCities)
            .withMinDistance(configuration.minDistanceBetweenCities)
            .generate()
    }

    private fun randomPopulation() = (Configuration.minCityPopulation..Configuration.maxCityPopulation).random()

    private fun Position.isInvalid(existingLocations: Set<Location>): Boolean =
        existingLocations.filter { e -> e.position.distanceTo(this) < Configuration.minDistanceBetweenCities }.any()

    object NameGenerator {

        val consonants = "abcdefghijklmnopqrstuvwxyz"
        val vowels = "aeiou"

        fun generate(): String {
            val builder = StringBuilder()
            val size = (3..12).random()
            for (i in 1..size) {
                builder.append(generateLetter())
            }
            return builder.toString()
        }

        private fun generateLetter(): Char {
            val rand = (0..100).random()
            return if (rand <= 50) vowels.random()
            else consonants.random()
        }
    }
}
