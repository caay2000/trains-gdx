package com.github.caay2000.trains.world.`object`.entity

object EntitySupport {

    class TestWagonType(
        override val cargoType: CargoType,
        override val capacity: Int,
        override val loadTimeUnit: Float
    ) : WagonType

    fun wagon(type: WagonType, load: Int = 0): Wagon {
        val wagon = Wagon(type)
        wagon.load = load
        return wagon
    }

    val PAX_20_WAGON = TestWagonType(CargoType.PAX, 20, 20F)
    val PAX_20_WAGON_SLOW = TestWagonType(CargoType.PAX, 20, 40F)
}