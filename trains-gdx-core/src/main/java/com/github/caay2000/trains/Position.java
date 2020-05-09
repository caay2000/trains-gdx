package com.github.caay2000.trains;

import com.badlogic.gdx.math.Vector2;

import java.util.Objects;
import java.util.StringJoiner;

public class Position {

    private final Vector2 vec2d;

    public Position() {
        this.vec2d = Vector2.Zero;
    }

    public Position(Vector2 vec2d) {
        this.vec2d = vec2d;
    }

    public Position(int x, int y) {
        this.vec2d = new Vector2(x, y);
    }

    public Position(float x, float y) {
        this.vec2d = new Vector2(x, y);
    }

    public Position move(Position to, float distance) {
        if (distance > vec2d.dst(to.vec2d)) {
            return new Position(to.vec2d);
        }
        Vector2 diff = copy().vec2d.sub(to.vec2d).nor();
        diff = new Vector2(diff.x * -1, diff.y * -1);
        return new Position(copy().vec2d.mulAdd(diff, distance));
    }

    public Position copy() {
        return new Position(vec2d.x, vec2d.y);
    }

    public float distanceTo(Position end) {
        return vec2d.dst(end.vec2d);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Position.class.getSimpleName() + "[", "]")
                .add("x=" + vec2d.x)
                .add("y=" + vec2d.y)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(vec2d, position.vec2d);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vec2d);
    }
}
