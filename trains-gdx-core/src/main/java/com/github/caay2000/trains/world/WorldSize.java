package com.github.caay2000.trains.world;

import java.util.Set;

public class WorldSize {

    private int maxX = 0;
    private int maxY = 0;
    private int minX = 0;
    private int minY = 0;

    public WorldSize(Set<? extends WorldObject> objects) {

        for (WorldObject obj : objects) {
            updateSize(obj);
        }
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    private void updateSize(WorldObject obj) {
        if (obj.getPosition().getX() > maxX) {
            maxX = (int) obj.getPosition().getX();
        }
        if (obj.getPosition().getX() < minX) {
            minX = (int) obj.getPosition().getX();
        }
        if (obj.getPosition().getY() > maxY) {
            maxY = (int) obj.getPosition().getY();
        }
        if (obj.getPosition().getY() < minY) {
            minY = (int) obj.getPosition().getY();
        }
    }

}
