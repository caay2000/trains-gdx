package com.badlogic.test.java

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.badlogic.trainsgdx.core.Test

object TestDesktop {
    @JvmStatic
    fun main(args: Array<String>) {
        val config = LwjglApplicationConfiguration()
        LwjglApplication(Test(), config)
    }
}