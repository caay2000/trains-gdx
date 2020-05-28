package com.github.caay2000.trains.event

import com.badlogic.gdx.ai.msg.MessageManager

object EventBus {

    private val messageDispatcher: MessageManager = MessageManager.getInstance()

    init {
        messageDispatcher.addListener(EventBusHandler, MessageType.TRAINS_GDX_MESSAGE_TYPE.ordinal)
    }

    fun update() {

        messageDispatcher.update()
    }

    fun dispatchMessage(delay: Float = 0f, messageInfo: MessageInfo) {
        dispatch(delay = delay, message = messageInfo)
    }

    private fun dispatch(delay: Float = 0f, message: MessageInfo) {

        messageDispatcher.dispatchMessage(
            delay,
            messageDispatcher,
            MessageType.TRAINS_GDX_MESSAGE_TYPE.ordinal,
            MessageInfoWrapper(delay, message)
        )
    }
}

