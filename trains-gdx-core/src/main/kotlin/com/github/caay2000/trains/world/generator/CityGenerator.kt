package com.github.caay2000.trains.world.generator

import com.github.caay2000.trains.Configuration
import com.github.caay2000.trains.world.`object`.location.AbstractLocation
import com.github.caay2000.trains.world.position.Position
import com.github.caay2000.trains.world.`object`.location.city.City

object LocationGenerator {

    fun generateLocation(existingCities: Set<AbstractLocation>, configuration: Configuration = Configuration): AbstractLocation {
        val position = randomPosition(existingCities, configuration)
        if (position.isInvalid(existingCities)) {
            return generateLocation(existingCities, configuration)
        }
        val name = NameGenerator.generate()
        return City(position, name, randomPopulation())
    }

    private fun randomPosition(
        existingCities: Set<AbstractLocation>,
        configuration: Configuration
    ): Position {

        return if (existingCities.isEmpty()) Position()
        else PositionGenerator
            .withCenter(existingCities.random().position)
            .withMaxDistance(configuration.maxDistanceBetweenCities)
            .withMinDistance(configuration.minDistanceBetweenCities)
            .generate()
    }

    private fun randomPopulation() = (Configuration.minLocationPopulation..Configuration.maxLocationPopulation).random()

    private fun Position.isInvalid(existingCities: Set<AbstractLocation>): Boolean =
        existingCities.filter { e -> e.position.distanceTo(this) < Configuration.minDistanceBetweenCities }.any()

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
