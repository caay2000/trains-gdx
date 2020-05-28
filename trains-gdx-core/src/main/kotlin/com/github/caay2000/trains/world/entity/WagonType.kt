package com.github.caay2000.trains.world.entity

import com.github.caay2000.trains.world.CargoType

enum class WagonType {

    DEFAULT_PAX(40, CargoType.PAX, 1f / 40f),
    DEFAULT_MAIL(25, CargoType.MAIL, 2f / 25f);

    val capacity: Int
    val cargoType: CargoType
    val loadTimeUnit: Float

    constructor(capacity: Int, cargoType: CargoType, loadTimeUnit: Float) {
        this.capacity = capacity
        this.cargoType = cargoType
        this.loadTimeUnit = loadTimeUnit
    }
}