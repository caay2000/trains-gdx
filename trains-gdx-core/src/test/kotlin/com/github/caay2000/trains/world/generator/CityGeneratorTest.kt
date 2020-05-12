package com.github.caay2000.trains.world.generator

import com.github.caay2000.trains.Configuration
import com.github.caay2000.trains.world.City
import com.github.caay2000.trains.world.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CityGeneratorTest {

    @Test
    fun `first city should be placed at 0,0`() {

        val city = CityGenerator.generateCity(setOf())
        assertThat(city.position).isEqualTo(Position())
    }

    @Test
    fun `second city should be placed around 0,0`() {

        val existingCity = City(Position(0, 0), 1)

        val city = CityGenerator.generateCity(setOf(existingCity))

        assertThat(city.position.distanceTo(Position())).isGreaterThan(Configuration.minDistanceBetweenCities.toFloat())
        // assertThat(city.position.distanceTo(Position())).isLessThan(Configuration.maxDistanceBetweenCities.toFloat())
    }
}