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
    fun `nextStop works ok at end of line`() {
        val start = randomLocation()
        val next = randomLocation()
        val sut = Route(start, next)
        sut.update()
        assertThat(sut.nextStop()).isEqualTo(start)
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

    @Test
    fun `remainingStops work ok`() {
        val a = randomLocation()
        val b = randomLocation()
        val c = randomLocation()
        val d = randomLocation()

        val sut = Route(a, b, c, d)
        sut.update()
        assertThat(sut.remainingStops()).containsOnly(c, d)
    }

    @Test
    fun `remainingStops work ok at endOfLine`() {
        val a = randomLocation()
        val b = randomLocation()
        val c = randomLocation()
        val d = randomLocation()

        val sut = Route(a, b, c, d)
        sut.update()
        sut.update()
        sut.update()
        assertThat(sut.remainingStops()).containsOnly(a, b, c)
    }

    @Test
    fun `remainingStops work ok for 2 stops line`() {
        val a = randomLocation()
        val b = randomLocation()

        val sut = Route(a, b)
        assertThat(sut.remainingStops()).containsOnly(b)

        sut.update()
        assertThat(sut.remainingStops()).containsOnly(a)
    }
}