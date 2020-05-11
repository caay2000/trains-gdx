package com.github.caay2000.trains.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.caay2000.trains.world.City;
import com.github.caay2000.trains.world.Route;
import com.github.caay2000.trains.world.Train;
import com.github.caay2000.trains.world.World;

public class WorldRender {

    private final int screenMargin = 50;

    private final ShapeRenderer shapeRenderer;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    float width = 800, height, aspectRatio;


    public WorldRender() {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        height = width * aspectRatio;
        camera.position.set(width / 2, height / 2, 0);
        camera.viewportWidth = width / 2;
        camera.viewportHeight = height / 2;
    }

    public void render(World world) {

        camera.update();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glDisable(GL20.GL_BLEND);

        batch.setProjectionMatrix(camera.combined);

        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (City city : world.getCities()) {
            shapeRenderer.circle(
                    city.getPosition().getX() + xOffset(world),
                    city.getPosition().getY() + yOffset(world),
                    city.getPopulation() / 1000 + 1);
        }

        for (Route route : world.getRoutes()) {
            shapeRenderer.line(route.getStart().getPosition().getX() + xOffset(world),
                    route.getStart().getPosition().getY() + yOffset(world),
                    route.getEnd().getPosition().getX() + xOffset(world),
                    route.getEnd().getPosition().getY() + yOffset(world));
        }

        shapeRenderer.setColor(Color.RED);
        for (Train train : world.getTrains()) {
            shapeRenderer.circle(
                    train.getPosition().getX() + xOffset(world),
                    train.getPosition().getY() + yOffset(world),
                    2);
        }

        shapeRenderer.end();
    }

    public int xOffset(World world) {
        return Math.abs(world.getSize().getMinX()) + screenMargin;
    }

    public int yOffset(World world) {
        return Math.abs(world.getSize().getMinY()) + screenMargin;
    }
}
