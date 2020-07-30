package com.github.caay2000.trains.world.`object`.location

import com.github.caay2000.trains.world.`object`.entity.CargoType.MAIL
import com.github.caay2000.trains.world.`object`.entity.CargoType.PAX
import com.github.caay2000.trains.world.`object`.location.LocationSupport.randomLocation
import com.github.caay2000.trains.world.`object`.location.LocationSupport.randomLocationWithDemands
import com.github.caay2000.trains.world.`object`.location.LocationSupport.randomLocationWithProduction
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class LocationTest {

    @Test
    fun `connection should work ok`() {

        val oneLocation = randomLocation()
        val anotherLocation = randomLocation()
        assertThat(oneLocation.connected()).isEqualTo(false)
        val result = oneLocation.addConnection(anotherLocation)
        assertThat(oneLocation.connected()).isEqualTo(true)
        assertThat(result).isTrue()
    }

    @Test
    fun `locationInRange works ok`() {

        val oneLocation = randomLocation()
        val anotherLocation = randomLocation()
        assertThat(oneLocation.locationsInRange).doesNotContain(anotherLocation)
        val result = oneLocation.addLocationInRange(anotherLocation)
        assertThat(oneLocation.locationsInRange).contains(anotherLocation)
        assertThat(result).isTrue()
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

        assertThat(sut.demands()).doesNotContain(PAX)
        sut.addDemand(PAX)
        assertThat(sut.demands()).contains(PAX)
        assertThat(sut.demand(PAX)).isTrue()

        assertThat(sut.demands()).doesNotContain(MAIL)
        assertThat(sut.demand(MAIL)).isFalse()
    }

    @Test
    fun `unloadCargo works ok`() {

        val sut = randomLocationWithDemands(PAX)

        assertThat(sut.unloadedCargo(PAX)).isEqualTo(0)
        sut.unloadCargo(PAX, 20)
        assertThat(sut.unloadedCargo(PAX)).isEqualTo(20)
    }

    @Test
    fun `consumeDemand works ok`() {

        val sut = randomLocationWithDemands(PAX)
        sut.unloadCargo(PAX, 20)
        assertThat(sut.unloadedCargo(PAX)).isEqualTo(20)

        assertThat(sut.consumeDemand(PAX, 20)).isTrue()
        assertThat(sut.unloadedCargo(PAX)).isEqualTo(0)

        assertThat(sut.consumeDemand(PAX, 30)).isFalse()
        assertThat(sut.consumeDemand(MAIL, 30)).isFalse()
    }

    @Test
    fun `produces works ok`() {

        val sut = randomLocation()
        assertThat(sut.produces()).isEmpty()

        sut.increaseProduction(PAX, 10)
        assertThat(sut.produces()).containsEntry(PAX, 10)
    }

    @Test
    fun `consumeProduction works ok`() {

        val sut = randomLocationWithProduction(listOf(Pair(PAX, 50)))
        assertThat(sut.produces()).containsEntry(PAX, 50)

        assertThat(sut.consumeProduction(PAX, 30)).isEqualTo(30)
        assertThat(sut.produces()).containsEntry(PAX, 20)

        assertThat(sut.consumeProduction(PAX, 30)).isEqualTo(20)
        assertThat(sut.produces()).containsEntry(PAX, 0)

        assertThat(sut.consumeProduction(MAIL, 30)).isEqualTo(0)
    }
}