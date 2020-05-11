package com.badlogic.trainsgdx.core

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL30
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Test : ApplicationListener {
    var texture: Texture? = null
    var batch: SpriteBatch? = null
    var elapsed = 0f
    override fun create() {
        texture = Texture(Gdx.files.internal("libgdx-logo.png"))
        batch = SpriteBatch()
    }

    override fun resize(width: Int, height: Int) {}
    override fun render() {
        elapsed += Gdx.graphics.deltaTime
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f)
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT)
        batch!!.begin()
        batch!!.draw(texture, 100 + 100 * Math.cos(elapsed.toDouble()).toFloat(), 100 + 25 * Math.sin(elapsed.toDouble()).toFloat())
        batch!!.end()
    }

    override fun pause() {}
    override fun resume() {}
    override fun dispose() {}
}