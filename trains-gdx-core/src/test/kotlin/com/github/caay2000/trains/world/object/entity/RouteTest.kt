package com.github.caay2000.trains.world.`object`.entity

import com.github.caay2000.trains.world.`object`.location.LocationSupport.randomLocation
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RouteTest {

    @Test
    fun `could have more than 2 stations`() {
        val sut = Route(randomLocation(), randomLocation(), randomLocation())
        assertThat(sut.cities).hasSize(3)
    }

    // TODO should check cities are connected and max distance is ok

    @Test
    fun `currentStop works ok for first city`() {
        val start = randomLocation()
        val sut = Route(start, randomLocation(), randomLocation())
        assertThat(sut.currentStop()).isEqualTo(start)
    }

    @Test
    fun `nextStop works ok`() {
        val start = randomLocation()
        val next = randomLocation()
        val sut = Route(start, next, randomLocation())
        assertThat(sut.nextStop()).isEqualTo(next)
    }

    @Test
    fun `nextStop works twice`() {
        val start = randomLocation()
        val next = randomLocation()
        val sut = Route(start, next, randomLocation())
        assertThat(sut.nextStop()).isEqualTo(next)
        assertThat(sut.nextStop()).isEqualTo(next)
    }

    @Test
    fun `updateRoute works ok`() {
        val start = randomLocation()
        val next = randomLocation()

        val sut = Route(start, next, randomLocation())
        sut.update()

        assertThat(sut.currentStop()).isEqualTo(next)
    }

    @Test
    fun `nextStop works ok after updateRoute`() {
        val start = randomLocation()
        val next = randomLocation()
        val following = randomLocation()

        val sut = Route(start, next, following)
        sut.update()

        assertThat(sut.nextStop()).isEqualTo(following)
    }
}