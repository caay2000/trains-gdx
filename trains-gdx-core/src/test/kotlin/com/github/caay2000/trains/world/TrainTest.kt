package com.github.caay2000.trains.world

import com.github.caay2000.trains.world.entity.Route
import com.github.caay2000.trains.world.location.City
import com.github.caay2000.trains.world.entity.Train
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TrainTest {

    private val aCity = City("aCity", Position(), 1)
    private val anotherCity = City("anotherCity", Position(10, 0), 1)
    private val aRoute = Route(aCity, anotherCity)

    @Test
    fun `move moves the train`() {

        val sut = Train(1f, aRoute, listOf())
        sut.update(1f)
        assertThat(sut.position).isEqualTo(Position(1, 0))
    }

    @Test
    fun `move stops the train at the station`() {

        val sut = Train(11f, aRoute, listOf())
        sut.update(1f)
        assertThat(sut.position).isEqualTo(Position(10, 0))
    }


}