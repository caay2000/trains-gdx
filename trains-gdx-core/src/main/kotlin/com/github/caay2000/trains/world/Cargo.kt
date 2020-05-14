package com.github.caay2000.trains.world

class Cargo(val type: CargoType) {

    var quantity = 0
        private set

    fun add(quantity: Int) {
        this.quantity += quantity
    }

    fun sub(quantity: Int) {
        this.quantity -= quantity
    }

    override fun toString(): String {
        return "Cargo(type=$type, quantity=$quantity)"
    }
}