package com.github.caay2000.trains.world.entity

import com.github.caay2000.trains.world.location.City

class Route(var start: City, var end: City) {

    fun endRoute() {
        this.start = this.end.also { this.end = this.start }
    }
}