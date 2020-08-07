package com.github.caay2000.trains.world.`object`.entity.strategy

import com.github.caay2000.trains.world.`object`.entity.CargoType.PAX
import com.github.caay2000.trains.world.`object`.entity.Entity
import com.github.caay2000.trains.world.`object`.entity.EntitySupport.PAX_20_WAGON
import com.github.caay2000.trains.world.`object`.entity.EntitySupport.PAX_20_WAGON_SLOW
import com.github.caay2000.trains.world.`object`.entity.EntitySupport.wagon
import com.github.caay2000.trains.world.`object`.entity.Route
import com.github.caay2000.trains.world.`object`.location.LocationSupport.randomLocation
import com.github.caay2000.trains.world.`object`.location.LocationSupport.randomLocationWithDemands
import com.github.caay2000.trains.world.`object`.location.LocationSupport.randomLocationWithProduction
import com.github.caay2000.trains.world.company.Company
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class LoadStrategyTest {

    @Test
    fun `should not load anything if no wagon`() {

        val entity = Entity(
            company = Company(),
            maxSpeed = 1F,
            route = Route(randomLocation(), randomLocation()),
            wagons = listOf()
        )

        val delta = LoadStrategy(entity).update(10F)

        assertThat(delta).isEqualTo(10F)
    }

    @Test
    fun `should not load available cargo if not demanded in route`() {

        val stop = randomLocationWithProduction(listOf(Pair(PAX, 10)))
        val entity = Entity(
            company = Company(),
            maxSpeed = 1F,
            route = Route(stop, randomLocation()),
            wagons = listOf(wagon(PAX_20_WAGON, 0))
        )

        val delta = LoadStrategy(entity).update(20F)

        assertThat(stop.produces()).containsEntry(PAX, 10)
        assertThat(entity.wagons[0].load).isEqualTo(0)
        assertThat(delta).isEqualTo(20F)
    }

    @Test
    fun `should load available cargo if demanded in route`() {

        val stop = randomLocationWithProduction(listOf(Pair(PAX, 10)))
        val entity = Entity(
            company = Company(),
            maxSpeed = 1F,
            route = Route(stop, randomLocationWithDemands(PAX)),
            wagons = listOf(wagon(PAX_20_WAGON, 0))
        )

        val delta = LoadStrategy(entity).update(40F)

        assertThat(stop.produces()).containsEntry(PAX, 0)
        assertThat(entity.wagons[0].load).isEqualTo(10)
        assertThat(delta).isEqualTo(30F)
    }

    @Test
    fun `should take slowest wagon time`() {

        val stop = randomLocationWithProduction(listOf(Pair(PAX, 100)))
        val entity = Entity(
            company = Company(),
            maxSpeed = 1F,
            route = Route(stop, randomLocationWithDemands(PAX)),
            wagons = listOf(wagon(PAX_20_WAGON, 0), wagon(PAX_20_WAGON_SLOW, 0))
        )

        val delta = LoadStrategy(entity).update(100F)

        assertThat(entity.wagons[0].load).isEqualTo(20)
        assertThat(entity.wagons[1].load).isEqualTo(20)
        assertThat(delta).isEqualTo(60F)
    }

    @Test
    fun `should not load other wagons if first wagons empty production`() {

        val stop = randomLocationWithProduction(listOf(Pair(PAX, 20)))
        val entity = Entity(
            company = Company(),
            maxSpeed = 1F,
            route = Route(stop, randomLocationWithDemands(PAX)),
            wagons = listOf(wagon(PAX_20_WAGON, 0), wagon(PAX_20_WAGON_SLOW, 0))
        )

        val delta = LoadStrategy(entity).update(100F)

        assertThat(entity.wagons[0].load).isEqualTo(20)
        assertThat(entity.wagons[1].load).isEqualTo(0)
        assertThat(delta).isEqualTo(80F)
    }

    @Test
    fun `should partially load multiple wagons`() {

        val stop = randomLocationWithProduction(listOf(Pair(PAX, 40)))
        val entity = Entity(
            company = Company(),
            maxSpeed = 1F,
            route = Route(stop, randomLocationWithDemands(PAX)),
            wagons = listOf(wagon(PAX_20_WAGON, 0), wagon(PAX_20_WAGON_SLOW, 0))
        )

        val delta = LoadStrategy(entity).update(10F)

        assertThat(entity.wagons[0].load).isEqualTo(10)
        assertThat(entity.wagons[1].load).isEqualTo(5)
        assertThat(delta).isEqualTo(0F)
    }

    @Test
    fun `should load ok when wagons are already half full or completelly full`() {

        val stop = randomLocationWithProduction(listOf(Pair(PAX, 40)))
        val entity = Entity(
            company = Company(),
            maxSpeed = 1F,
            route = Route(stop, randomLocationWithDemands(PAX)),
            wagons = listOf(wagon(PAX_20_WAGON, 10), wagon(PAX_20_WAGON_SLOW, 20))
        )

        val delta = LoadStrategy(entity).update(20F)

        assertThat(stop.produces()).containsEntry(PAX, 30)
        assertThat(entity.wagons[0].load).isEqualTo(20)
        assertThat(entity.wagons[1].load).isEqualTo(20)
        assertThat(delta).isEqualTo(10F)
    }
}