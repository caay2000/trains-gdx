package com.github.caay2000.trains.world.`object`.entity

class Wagon {

    val model: WagonType
    var load: Int = 0

    constructor(model: WagonType) {
        this.model = model
        this.load
    }
}

interface WagonType {
    val cargoType: CargoType
    val capacity: Int
    val loadTimeUnit: Float
}

enum class BasicWagonType : WagonType {

    DEFAULT_PAX(CargoType.PAX, 40, 1f),
    DEFAULT_MAIL(CargoType.MAIL, 25, 2f);

    override val capacity: Int
    override val cargoType: CargoType
    override val loadTimeUnit: Float

    constructor(cargoType: CargoType, capacity: Int, loadTimeUnit: Float) {
        this.capacity = capacity
        this.cargoType = cargoType
        this.loadTimeUnit = loadTimeUnit
    }
}