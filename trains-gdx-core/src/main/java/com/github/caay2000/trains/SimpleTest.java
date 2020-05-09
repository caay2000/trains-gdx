package com.github.caay2000.trains;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class SimpleTest implements ApplicationListener {

    private float delta = 0;
    private float elapsed = 0;
    private Route route5 = new Route(new Position(), new Position(10, 10));
    private Route route1 = new Route(new Position(), new Position(10, 0));
    private Route route2 = new Route(new Position(), new Position(-10, 0));
    private Route route3 = new Route(new Position(), new Position(0, 10));
    private Route route4 = new Route(new Position(), new Position(0, -10));
    private Train train1 = new Train(route1, 1d);
    private Train train2 = new Train(route2, 1d);
    private Train train3 = new Train(route3, 1d);
    private Train train4 = new Train(route4, 1d);
    private Train train5 = new Train(route5, 1d);
    private Train train6 = new Train(route1,  2d);

    @Override
    public void create() {

    }

    @Override
    public void resize(int i, int i1) {

    }

    public void render() {

        delta = Gdx.graphics.getDeltaTime();
        elapsed += delta;

        train1.move(delta);
        train2.move(delta);
        train3.move(delta);
        train4.move(delta);
        train5.move(delta);
        train6.move(delta);

        System.out.println(elapsed);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
