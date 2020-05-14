package com.github.caay2000.trains

import com.badlogic.gdx.Gdx

fun debug(condition: Boolean = true, message: () -> String) {
    if (condition) Gdx.app.debug("debug", message())
}