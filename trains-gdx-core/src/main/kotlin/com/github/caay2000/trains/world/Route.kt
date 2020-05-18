package com.github.caay2000.trains.world

class Route(var start: Location, var end: Location) {

    fun endRoute() {
        this.start = this.end.also { this.end = this.start }
    }

    fun nextStop() = this.end
}