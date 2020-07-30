package com.github.caay2000.trains.world.`object`.entity.strategy

import com.github.caay2000.trains.world.`object`.entity.CargoType.PAX
import com.github.caay2000.trains.world.`object`.entity.Entity
import com.github.caay2000.trains.world.`object`.entity.EntitySupport.PAX_20_WAGON
import com.github.caay2000.trains.world.`object`.entity.EntitySupport.PAX_20_WAGON_SLOW
import com.github.caay2000.trains.world.`object`.entity.EntitySupport.wagon
import com.github.caay2000.trains.world.`object`.entity.Route
import com.github.caay2000.trains.world.`object`.location.LocationSupport.randomLocation
import com.github.caay2000.trains.world.`object`.location.LocationSupport.randomLocationWithDemands
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class UnloadStrategyTest {

    @Test
    fun `should do nothing if no cargo`() {
        val stop = randomLocation()
        val entity = Entity(
            maxSpeed = 1F,
            route = Route(stop, randomLocation()),
            wagons = listOf(wagon(PAX_20_WAGON, 0))
        )

        val delta = UnloadStrategy(entity).update(10F)

        assertThat(delta).isEqualTo(10F)
    }

    @Test
    fun `should not unload not demanded cargo`() {

        val stop = randomLocation()
        val entity = Entity(
            maxSpeed = 1F,
            route = Route(stop, randomLocation()),
            wagons = listOf(wagon(PAX_20_WAGON, 20))
        )

        val delta = UnloadStrategy(entity).update(10F)

        assertThat(entity.wagons[0].load).isEqualTo(20)
        assertThat(stop.unloadedCargo(PAX)).isEqualTo(0)
        assertThat(delta).isEqualTo(10F)
    }

    @Test
    fun `should unload demanded cargo and wagon should be empty`() {

        val stop = randomLocationWithDemands(PAX)
        val entity = Entity(
            maxSpeed = 1F,
            route = Route(stop, randomLocationWithDemands(PAX)),
            wagons = listOf(wagon(PAX_20_WAGON, 20))
        )

        val delta = UnloadStrategy(entity).update(50F)

        assertThat(entity.wagons[0].load).isEqualTo(0)
        assertThat(stop.unloadedCargo(PAX)).isEqualTo(20)
        assertThat(delta).isEqualTo(30F)
    }

    @Test
    fun `should unload demanded cargo but just half of it because of delta`() {

        val stop = randomLocationWithDemands(PAX)
        val entity = Entity(
            maxSpeed = 1F,
            route = Route(stop, randomLocationWithDemands(PAX)),
            wagons = listOf(wagon(PAX_20_WAGON, 20))
        )

        val delta = UnloadStrategy(entity).update(10F)

        assertThat(entity.wagons[0].load).isEqualTo(10)
        assertThat(stop.unloadedCargo(PAX)).isEqualTo(10)
        assertThat(delta).isEqualTo(0F)
    }

    @Test
    fun `should return unload time for slowest wagon`() {

        val stop = randomLocationWithDemands(PAX)
        val entity = Entity(
            maxSpeed = 1F,
            route = Route(stop, randomLocationWithDemands(PAX)),
            wagons = listOf(wagon(PAX_20_WAGON, 20), wagon(PAX_20_WAGON_SLOW, 20), wagon(PAX_20_WAGON, 0))
        )

        val delta = UnloadStrategy(entity).update(50F)

        assertThat(entity.wagons[0].load).isEqualTo(0)
        assertThat(entity.wagons[1].load).isEqualTo(0)
        assertThat(stop.unloadedCargo(PAX)).isEqualTo(40)
        assertThat(delta).isEqualTo(10F)
    }

    @Test
    fun `should unload half wagon correctly`() {

        val stop = randomLocationWithDemands(PAX)
        val entity = Entity(
            maxSpeed = 1F,
            route = Route(stop, randomLocationWithDemands(PAX)),
            wagons = listOf(wagon(PAX_20_WAGON, 10), wagon(PAX_20_WAGON_SLOW, 5))
        )

        val delta = UnloadStrategy(entity).update(10F)

        assertThat(entity.wagons[0].load).isEqualTo(0)
        assertThat(entity.wagons[1].load).isEqualTo(0)
        assertThat(stop.unloadedCargo(PAX)).isEqualTo(15)
        assertThat(delta).isEqualTo(0F)
    }
}