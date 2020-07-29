package com.github.caay2000.trains.world.`object`.entity

import com.github.caay2000.trains.world.`object`.entity.CargoType.PAX
import com.github.caay2000.trains.world.`object`.entity.strategy.UnloadStrategy
import com.github.caay2000.trains.world.`object`.location.LocationSupport.randomLocation
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class UnloadStrategyTest {

    // @Test
    // fun `does nothing if no cargo`() {
    // }

    // @Test
    // fun `should not unload not demanded cargo`() {
    //
    //     val entity = Entity(
    //         maxSpeed = 1F,
    //         route = Route(randomLocation(), randomLocation()),
    //         wagons = listOf(wagon(PAX_WAGON, 20))
    //     )
    //
    //     UnloadStrategy(entity).update(20F)
    //
    //     assertThat(entity.wagons[0].load).isEqualTo(20)
    // }
    //
    // @Test
    // fun `should unload demanded cargo and wagon should be empty`() {
    //
    //     val entity = Entity(
    //         maxSpeed = 1F,
    //         route = Route(randomLocation(PAX), randomLocation(PAX)),
    //         wagons = listOf(wagon(PAX_WAGON, 20))
    //     )
    //
    //     UnloadStrategy(entity).update(20F)
    //
    //     assertThat(entity.wagons[0].load).isEqualTo(0)
    // }

    //
    // @Test
    // fun `should unload demanded cargo and city should receive that cargo`() {
    //
    //     val entity = entityWith(wagon(PAX, 20))
    //     val sut = UnloadStrategy(entity)
    //
    //     sut.update(20F)
    //
    //     assertThat(entity.wagons[0].load).isEqualTo(0)
    // }
    //
    class TestWagonType(
        override val cargoType: CargoType,
        override val capacity: Int,
        override val loadTimeUnit: Float
    ) : WagonType

    private fun wagon(type: WagonType, load: Int = 0): Wagon {
        val wagon = Wagon(type)
        wagon.load = load
        return wagon
    }
    //
    // private fun entityWith(vararg wagon: Wagon): Entity {
    //     return Entity(1F, randomRoute(), wagon.toList())
    // }
    //
    // private fun randomRoute(size: Int = 2): Route {
    //     val route = Route(randomLocation(), randomLocation())
    //     for (i in 2..size) {
    //         route.addStop(randomLocation())
    //     }
    //     return route
    // }

    private val PAX_WAGON = TestWagonType(PAX, 20, 20F)
}