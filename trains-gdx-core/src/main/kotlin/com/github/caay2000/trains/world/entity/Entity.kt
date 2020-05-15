package com.github.caay2000.trains.world.entity

import com.github.caay2000.trains.world.Position

interface Entity {

    val position: Position

    fun update(delta: Float)
}