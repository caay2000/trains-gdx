package com.github.caay2000.trains.world.generator

import com.github.caay2000.trains.Configuration
import com.github.caay2000.trains.world.`object`.location.city.City
import com.github.caay2000.trains.world.position.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class LocationGeneratorTest {

    @Test
    fun `first city should be placed at 0,0`() {

        val city = LocationGenerator.generateLocation(setOf())
        assertThat(city.position).isEqualTo(Position())
    }

    @Test
    fun `second city should be placed around 0,0`() {

        val existingLocation = City(Position(0, 0), "name", 1)

        val city = LocationGenerator.generateLocation(setOf(existingLocation))

        assertThat(city.position.distanceTo(Position())).isGreaterThan(Configuration.minDistanceBetweenCities.toFloat())
        // assertThat(city.position.distanceTo(Position())).isLessThan(Configuration.maxDistanceBetweenCities.toFloat())
    }
}