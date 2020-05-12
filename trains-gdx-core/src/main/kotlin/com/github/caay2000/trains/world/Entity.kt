package com.github.caay2000.trains.world

interface Entity {

    val position: Position

    fun update(delta: Float)
}