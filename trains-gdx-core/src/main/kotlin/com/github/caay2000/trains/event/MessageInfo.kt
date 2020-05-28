package com.github.caay2000.trains.event

import com.github.caay2000.trains.world.entity.Train

sealed class MessageInfo

class MessageTrainInfo(val train: Train) : MessageInfo()
