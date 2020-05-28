package com.github.caay2000.trains.event

import com.github.caay2000.trains.world.entity.Train

sealed class MessageInfo

class TrainEvent(val train: Train, val status: Train.EntityStatus) : MessageInfo()
