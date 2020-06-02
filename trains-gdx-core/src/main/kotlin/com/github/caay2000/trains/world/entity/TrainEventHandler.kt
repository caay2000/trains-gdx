package com.github.caay2000.trains.world.entity

import com.github.caay2000.trains.event.EntityMoving
import com.github.caay2000.trains.event.EntityStationReadyToUnloadEvent
import com.github.caay2000.trains.event.EntityStationStationReadyEvent
import com.github.caay2000.trains.event.EntityStationStationStopEvent
import com.github.caay2000.trains.event.EventBus
import com.github.caay2000.trains.event.EventListener
import com.github.caay2000.trains.event.EventMessage
import com.github.caay2000.trains.event.MessageType
import com.github.caay2000.trains.event.TrainEvent2
import com.github.caay2000.trains.info

class TrainEventHandler(private val processor: TrainEventProcessor) : EventListener {

    init {
        EventBus.addListener(this, MessageType.TRAIN_EVENT)
    }

    override fun handleMessage(message: EventMessage) {
        info { message.toString() }
        when (message.messageInfo) {
            is EntityStationStationStopEvent -> processor.stationStop(message.messageInfo.entity, message.deltaRemainder())
            is EntityStationReadyToUnloadEvent -> processor.startUnloading(message.messageInfo.entity, message.deltaRemainder())
            is EntityStationStationReadyEvent -> processor.stationLeave(message.messageInfo.entity, message.deltaRemainder())
            is EntityMoving -> processor.move(message.messageInfo.entity, message.deltaRemainder())
            is TrainEvent2 -> TODO()
        }
    }
}