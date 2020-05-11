package com.github.caay2000.trains.world;

import com.github.caay2000.trains.common.Position;

public interface WorldObject {

    Position getPosition();

    float distanceTo(WorldObject to);
}
