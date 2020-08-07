package com.github.caay2000.trains.world

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ai.GdxAI
import com.github.caay2000.trains.Configuration

object GlobalData {

    lateinit var world: World

    var current: Float = 0f
        private set
    var delta: Float = 0f
        private set

    private val gameSpeed = Configuration.gameSpeed

    fun update() {
        this.delta = Gdx.graphics.deltaTime * this.gameSpeed
        this.current += delta
        GdxAI.getTimepiece().update(delta)
        // EventBus.update()
    }
}