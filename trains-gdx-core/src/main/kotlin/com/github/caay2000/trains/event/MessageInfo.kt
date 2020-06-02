package com.github.caay2000.trains.event

import com.github.caay2000.trains.world.CargoType
import com.github.caay2000.trains.world.entity.Entity
import com.github.caay2000.trains.world.entity.Train

sealed class MessageInfo {
    abstract val type: MessageType
}

sealed class EntityEvent : MessageInfo() {
    abstract val entity: Entity
    override val type = MessageType.TRAIN_EVENT
}

data class EntityStationStationStopEvent(override val entity: Entity) : EntityEvent()
data class EntityStationReadyToUnloadEvent(override val entity: Entity) : EntityEvent()
data class EntityStationCargoLoaded(override val entity: Entity, val cargo: CargoType, val quantity: Int) : EntityEvent()
data class EntityStationStationReadyEvent(override val entity: Entity) : EntityEvent()
data class EntityMoving(override val entity: Entity) : EntityEvent()

class TrainEvent2(val train: Train, val status: Train.EntityStatus) : MessageInfo() {
    override val type = MessageType.TRAINS_GDX_MESSAGE_TYPE
}
