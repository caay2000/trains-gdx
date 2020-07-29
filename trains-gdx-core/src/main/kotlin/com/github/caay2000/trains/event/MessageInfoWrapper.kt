// package com.github.caay2000.trains.event
//
// import com.github.caay2000.trains.world.GlobalData
//
// class MessageInfoWrapper(val delay: Float = 0f, val messageInfo: MessageInfo) {
//
//     val sendTimestamp = GlobalData.current
//     val receiveTimestamp = GlobalData.current + delay
//
//     fun missingDelta() = GlobalData.current - receiveTimestamp
// }