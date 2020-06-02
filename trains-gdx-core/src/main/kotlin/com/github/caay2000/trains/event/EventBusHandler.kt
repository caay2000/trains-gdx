package com.github.caay2000.trains.event

import com.badlogic.gdx.ai.msg.Telegram
import com.badlogic.gdx.ai.msg.Telegraph
import com.github.caay2000.trains.debug

object EventBusHandler : Telegraph {

    override fun handleMessage(message: Telegram): Boolean {

        val wrapper = message.extraInfo as MessageInfoWrapper
        val event = wrapper.messageInfo
        debug { "handling message timestamp: ${wrapper.sendTimestamp}, delay: ${wrapper.delay}, timestampReceived: ${wrapper.receiveTimestamp}" }
        when (event) {
            is TrainEvent2 -> {
                event.train.update(wrapper.missingDelta(), event.status)
            }
        }
        return true
    }
}