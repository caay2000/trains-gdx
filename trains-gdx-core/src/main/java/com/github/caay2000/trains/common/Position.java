package com.github.caay2000.trains.common;

import com.badlogic.gdx.math.Vector2;

import java.util.Objects;
import java.util.StringJoiner;

import static com.github.caay2000.trains.util.RandomGenerator.randomSignedInt;

public class Position {

    protected final Vector2 vec2d;

    public Position() {
        this.vec2d = Vector2.Zero;
    }

    public Position(Position position) {
        this.vec2d = position.vec2d;
    }

    protected Position(Vector2 vec2d) {
        this.vec2d = vec2d;
    }

    public Position(int x, int y) {
        this.vec2d = new Vector2(x, y);
    }

    public Position(float x, float y) {
        this.vec2d = new Vector2(x, y);
    }

    public float getX() {
        return vec2d.x;
    }

    public float getY() {
        return vec2d.y;
    }

    public float distanceTo(Position to) {
        return vec2d.dst(to.vec2d);
    }

    protected Position copy() {
        return new Position(vec2d.x, vec2d.y);
    }

    public Position move(Position to, float distance) {
        if (distance > vec2d.dst(to.vec2d)) {
            return to.copy();
        }
        Vector2 diff = copy().vec2d.sub(to.vec2d).nor();
        diff = new Vector2(diff.x * -1, diff.y * -1);
        return new Position(copy().vec2d.mulAdd(diff, distance));
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

    public static class Generator {

        private Position center = new Position();
        private int maxDistance = 100;
        private int minDistance = 100;

        public Generator withCenter(Position center) {
            this.center = center;
            return this;
        }

        public Generator withMaxDistance(int maxDistance) {
            this.maxDistance = maxDistance;
            return this;
        }

        public Generator withMinDistance(int minDistance) {
            this.minDistance = minDistance;
            return this;
        }

        public Position generate() {
            int rangeX = (int) center.getX() + maxDistance;
            int rangeY = (int) center.getY() + maxDistance;

            int x = randomSignedInt(Math.negateExact(rangeX), rangeX);
            int y = randomSignedInt(Math.negateExact(rangeY), rangeY);

            Position newPosition = new Position(x, y);
            if (newPosition.distanceTo(center) > minDistance) {
                return newPosition;
            }
            return generate();
        }
    }
}
