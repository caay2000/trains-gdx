package com.github.caay2000.trains.world.entity

class Wagon {

    val model: WagonType
    var load: Int = 0

    constructor(model: WagonType) {
        this.model = model
        this.load
    }
}