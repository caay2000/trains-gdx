package com.github.caay2000.trains.world.`object`.entity

import com.github.caay2000.trains.world.`object`.location.AbstractLocation
import com.github.caay2000.trains.world.`object`.location.Location
import com.github.caay2000.trains.world.position.Position
import kotlin.random.Random

object EntitySupport {



    private fun randomName() = Random.nextBytes(4).toString()


    class TestLocation(
        override val position: Position
    ) : AbstractLocation(position) {

        override fun update(delta: Float) {
            TODO("Not yet implemented")
        }
    }
}