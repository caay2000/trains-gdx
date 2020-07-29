package com.github.caay2000.trains.world

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ai.GdxAI
import com.github.caay2000.trains.Configuration
// import com.github.caay2000.trains.event.EventBus

object GlobalData {

    var current: Float = 0f
        private set
    var delta: Float = 0f
        private set

    val gameSpeed = Configuration.gameSpeed

    fun update() {

        this.delta = Gdx.graphics.deltaTime * this.gameSpeed
        this.current += delta
        GdxAI.getTimepiece().update(delta)
        // EventBus.update()
    }
}