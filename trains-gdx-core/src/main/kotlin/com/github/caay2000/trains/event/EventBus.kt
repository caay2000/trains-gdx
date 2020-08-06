// package com.github.caay2000.trains.event
//
// import com.badlogic.gdx.ai.msg.MessageManager
//
// object EventBus {
//
//     private val messageDispatcher: MessageManager = MessageManager.getInstance()
//
//     init {
//         messageDispatcher.addListener(EventBusHandler, MessageType.TRAINS_GDX_MESSAGE_TYPE.ordinal)
//     }
//
//     fun update() {
//
//         messageDispatcher.update()
//     }
//
//     fun addListener(listener: EventListener, messageType: MessageType) {
//         messageDispatcher.addListener(listener, messageType.ordinal)
//     }
//
//     fun dispatchMessage(message: MessageInfo, delta: Float = 0f, delay: Float = 0f) {
//         if (delta > 0 && delay > 0) {
//             dispatch(delay = delay - delta, message = message)
//         } else {
//             dispatch(delta = delta, delay = delay, message = message)
//         }
//     }
//
//     private fun dispatch(delta: Float = 0f, delay: Float = 0f, message: MessageInfo) {
//
//         messageDispatcher.dispatchMessage(
//             delay,
//             messageDispatcher,
//             message.type.ordinal,
//             EventMessage(delta, delay, message)
//         )
//     }
// }
//
