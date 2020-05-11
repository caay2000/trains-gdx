package com.github.caay2000.trains

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.github.caay2000.trains.render.WorldRender
import com.github.caay2000.trains.world.World
import com.github.caay2000.trains.world.WorldConfiguration

class SimpleTest : ApplicationListener {
    private var world: World? = null
    private var worldRender: WorldRender? = null
    override fun create() {
        world = World(WorldConfiguration.worldConfiguration()
//                .withNumberOfCities(30)
//                .withMaxDistanceBetweenCities(150)
//                .withMinDistanceBetweenCities(100)
//                .withMaxRouteDistance(200)
        )
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