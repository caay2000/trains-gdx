package com.github.caay2000.trains.world.generator

import com.github.caay2000.trains.Configuration
import com.github.caay2000.trains.world.World
import com.github.caay2000.trains.world.generator.CityGenerator.generateCity

object WorldGenerator {

    private val worldBuilder = World.Builder()

    fun generate(): World {

        this.generateCities()

        return worldBuilder.build()
    }

    private fun generateCities() {

        for (i in 1..Configuration.numberOfCities) {
            worldBuilder.addCity(generateCity(worldBuilder.cities()))
        }
    }
}