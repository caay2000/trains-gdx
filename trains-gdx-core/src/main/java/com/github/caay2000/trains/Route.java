package com.github.caay2000.trains;

public class Route {

    private Position start;
    private Position end;

    public Route(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }
}
