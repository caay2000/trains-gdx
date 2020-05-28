package com.github.caay2000.trains.event

import com.badlogic.gdx.ai.msg.Telegram
import com.badlogic.gdx.ai.msg.Telegraph
import com.github.caay2000.trains.debug
import com.github.caay2000.trains.world.entity.Train

object EventBusHandler : Telegraph {
    override fun handleMessage(message: Telegram): Boolean {

        val wrapper = message.extraInfo as MessageInfoWrapper
        debug { "handling message timestamp: ${wrapper.sendTimestamp}, delay: ${wrapper.delay}, timestampReceived: ${wrapper.receiveTimestamp}" }
        when (wrapper.messageInfo) {
            is MessageTrainInfo -> {
                wrapper.messageInfo.train.update(wrapper.missingDelta(), Train.EntityStatus.UNLOADING)
            }
        }
        return true
    }
}