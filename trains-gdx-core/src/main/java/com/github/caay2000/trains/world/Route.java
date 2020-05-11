package com.github.caay2000.trains.world;

import com.github.caay2000.trains.common.Position;

public class Route {

    private WorldObject start;
    private WorldObject end;

    public Route(WorldObject start, WorldObject end) {
        this.start = start;
        this.end = end;
    }

    public WorldObject getStart() {
        return start;
    }

    public WorldObject getEnd() {
        return end;
    }
}
