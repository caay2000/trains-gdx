package com.github.caay2000.trains.world;

import com.github.caay2000.trains.common.Position;

public class Train {

    private Route route;
    private Position position;
    private double speed;

    public Train(Route route, double speed) {
        this.route = route;
        this.position = new Position(route.getStart().getPosition());
        this.speed = speed;
    }

    public Route getRoute() {
        return route;
    }

    public Position getPosition() {
        return position;
    }

    public double getSpeed() {
        return speed;
    }

    public void move(float elapsed) {
        updatePosition(elapsed);
    }

    private void updatePosition(float elapsed) {
        float distance = position.distanceTo(route.getEnd().getPosition());
        if (speed * elapsed > distance) {
            calculateNextRoute();
            elapsed = calculateElpsedMissing(elapsed, distance);
        }
        position = position.move(route.getEnd().getPosition(), (float) (elapsed * speed));
    }

    private float calculateElpsedMissing(float elapsed, float distance) {
        distance = (float) (elapsed * speed) - distance;
        elapsed = (elapsed * distance) / (float) (speed * elapsed);
        return elapsed;
    }

    private void calculateNextRoute() {
        position = new Position(route.getEnd().getPosition());
        route = new Route(route.getEnd(), route.getStart());
    }

}


