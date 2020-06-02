package com.github.caay2000.trains.event

import com.badlogic.gdx.ai.msg.Telegram
import com.badlogic.gdx.ai.msg.Telegraph

interface EventListener : Telegraph {

    override fun handleMessage(telegram: Telegram): Boolean {

        handleMessage(telegram.extraInfo as EventMessage)
        return true
    }

    fun handleMessage(message: EventMessage)
}