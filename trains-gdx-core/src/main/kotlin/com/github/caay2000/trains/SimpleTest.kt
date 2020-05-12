package com.github.caay2000.trains

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.github.caay2000.trains.render.WorldRender
import com.github.caay2000.trains.world.World
import com.github.caay2000.trains.world.generator.WorldGenerator

class SimpleTest : ApplicationListener {
    private var world: World? = null
    private var worldRender: WorldRender? = null
    override fun create() {

        Configuration.numberOfCities = 50

        world = WorldGenerator.generate()
        worldRender = WorldRender()
    }

    override fun resize(i: Int, i1: Int) {}
    override fun render() {

        val delta = Gdx.graphics.deltaTime
        world!!.update(delta)
        worldRender!!.render(world!!)
    }

    override fun pause() {}
    override fun resume() {}
    override fun dispose() {}
}