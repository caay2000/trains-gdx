package com.github.caay2000.trains;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.github.caay2000.trains.render.WorldRender;
import com.github.caay2000.trains.world.World;

import static com.github.caay2000.trains.world.WorldConfiguration.worldConfiguration;

public class SimpleTest implements ApplicationListener {

    private World world;
    private WorldRender worldRender;

    @Override
    public void create() {

        world = new World(worldConfiguration()
                .withNumberOfCities(30)
                .withMaxDistanceBetweenCities(150)
                .withMinDistanceBetweenCities(100)
                .withMaxRouteDistance(200)
        );
        worldRender = new WorldRender();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {

        float delta = Gdx.graphics.getDeltaTime();
        world.update(delta);
        worldRender.render(world);
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
