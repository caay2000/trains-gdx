package com.github.caay2000.trains.world

import com.github.caay2000.trains.world.`object`.AbstractWorldObject
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class WorldObjectTest {

    @Test
    fun `should have an unique id`() {

        val sut = TestWorldObject()

        val expectedId = sut.id

        assertThat(sut.id).isEqualTo(expectedId)
    }

    private class TestWorldObject : AbstractWorldObject() {
        override fun update(delta: Float) {
        }
    }
}