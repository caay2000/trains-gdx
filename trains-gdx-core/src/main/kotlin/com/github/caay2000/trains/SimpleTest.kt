package com.github.caay2000.trains

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.github.caay2000.trains.render.WorldRender
import com.github.caay2000.trains.world.World
import com.github.caay2000.trains.world.generator.WorldGenerator

class SimpleTest : ApplicationListener {
    private lateinit var world: World
    private lateinit var worldRender: WorldRender
    override fun create() {

        Configuration.numberOfCities = 50
        Gdx.app.logLevel = Application.LOG_DEBUG

        world = WorldGenerator.generate()
        worldRender = WorldRender()
    }

    override fun resize(i: Int, i1: Int) {}
    override fun render() {

        val delta = Gdx.graphics.deltaTime
        world.update(delta)
        worldRender.render(world, delta)
    }

    override fun pause() {}
    override fun resume() {}
    override fun dispose() {}
}