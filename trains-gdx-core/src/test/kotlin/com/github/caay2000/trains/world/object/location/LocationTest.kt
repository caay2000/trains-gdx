package com.github.caay2000.trains.world.`object`.location

import com.github.caay2000.trains.world.`object`.entity.CargoType
import com.github.caay2000.trains.world.`object`.location.LocationSupport.randomLocation
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class LocationTest {

    @Test
    fun `connection should work ok`() {

        val oneLocation = randomLocation()
        val anotherLocation = randomLocation()
        assertThat(oneLocation.connected()).isEqualTo(false)
        oneLocation.addConnection(anotherLocation)
        assertThat(oneLocation.connected()).isEqualTo(true)
    }

    @Test
    fun `locationInRange works ok`() {

        val oneLocation = randomLocation()
        val anotherLocation = randomLocation()
        assertThat(oneLocation.locationsInRange).doesNotContain(anotherLocation)
        oneLocation.addLocationInRange(anotherLocation)
        assertThat(oneLocation.locationsInRange).contains(anotherLocation)
    }

    @Test
    fun `distanceTo works ok`() {

        val oneLocation = randomLocation()
        val anotherLocation = randomLocation()
        assertThat(oneLocation.distanceTo(anotherLocation))
            .isEqualTo(oneLocation.position.distanceTo(anotherLocation.position))
    }

    @Test
    fun `demands works ok`() {

        val sut = randomLocation()

        assertThat(sut.demands()).doesNotContain(CargoType.PAX)
        sut.addDemand(CargoType.PAX)
        assertThat(sut.demands()).contains(CargoType.PAX)
        assertThat(sut.demand(CargoType.PAX)).isTrue()

        assertThat(sut.demands()).doesNotContain(CargoType.MAIL)
        assertThat(sut.demand(CargoType.MAIL)).isFalse()
    }

    @Test
    fun `unloadCargo works ok`() {

        val sut = randomLocation(CargoType.PAX)

        assertThat(sut.unloadedCargo(CargoType.PAX)).isEqualTo(0)
        sut.unloadCargo(CargoType.PAX, 20)
        assertThat(sut.unloadedCargo(CargoType.PAX)).isEqualTo(20)
    }

    @Test
    fun `consumeDemand works ok`() {

        val sut = randomLocation(CargoType.PAX)
        sut.unloadCargo(CargoType.PAX, 20)
        assertThat(sut.unloadedCargo(CargoType.PAX)).isEqualTo(20)

        assertThat(sut.consumeDemand(CargoType.PAX, 20)).isTrue()
        assertThat(sut.unloadedCargo(CargoType.PAX)).isEqualTo(0)

        assertThat(sut.consumeDemand(CargoType.PAX, 30)).isFalse()
        assertThat(sut.consumeDemand(CargoType.MAIL, 30)).isFalse()
    }
}