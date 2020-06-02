package com.github.caay2000.trains

import com.badlogic.gdx.Gdx
import com.github.caay2000.trains.world.GlobalData

fun info(condition: Boolean = true, message: () -> String) {
    if (condition) Gdx.app.log("info", "[${GlobalData.current}] ${message()}")
}

fun debug(condition: Boolean = true, message: () -> String) {
    if (condition) Gdx.app.debug("debug", "[${GlobalData.current}] ${message()}")
}