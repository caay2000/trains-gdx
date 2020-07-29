// package com.github.caay2000.trains.event
//
// import com.badlogic.gdx.ai.msg.Telegram
// import com.github.caay2000.trains.world.GlobalData
//
// class EventMessage(val delta: Float = 0f, private val delay: Float = 0f, val messageInfo: MessageInfo) : Telegram() {
//
//     val sendTimestamp = GlobalData.current
//     private val receiveTimestamp = GlobalData.current + delay
//
//     fun deltaRemainder() = delta + GlobalData.current - receiveTimestamp
//     override fun toString(): String {
//         return "${this.messageInfo::class.simpleName}(delta=$delta, delay=$delay, sendTimestamp=$sendTimestamp, receiveTimestamp=$receiveTimestamp, deltaRemainder=${deltaRemainder()} messageInfo=$messageInfo)"
//     }
// }