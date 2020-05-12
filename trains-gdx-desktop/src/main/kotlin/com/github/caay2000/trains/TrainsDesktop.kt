package com.github.caay2000.trains

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

object TrainsDesktop {
    @JvmStatic
    fun main(args: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.height = 1200
        config.width = 1600
        LwjglApplication(SimpleTest(), config)
    }
}