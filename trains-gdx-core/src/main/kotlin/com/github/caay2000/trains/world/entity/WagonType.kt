package com.github.caay2000.trains.world.entity

import com.github.caay2000.trains.world.CargoType

enum class WagonType {

    DEFAULT_PAX(40, CargoType.PAX),
    DEFAULT_MAIL(25, CargoType.MAIL);

    val capacity: Int
    val type: CargoType

    constructor(capacity: Int, type: CargoType) {
        this.capacity = capacity
        this.type = type
    }
}