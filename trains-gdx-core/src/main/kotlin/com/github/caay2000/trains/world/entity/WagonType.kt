package com.github.caay2000.trains.world.entity

import com.github.caay2000.trains.world.CargoType

enum class WagonType {

    DEFAULT_PAX(40, CargoType.PAX),
    DEFAULT_MAIL(25, CargoType.MAIL);

    val capacity: Int
    val cargoType: CargoType

    constructor(capacity: Int, cargoType: CargoType) {
        this.capacity = capacity
        this.cargoType = cargoType
    }
}