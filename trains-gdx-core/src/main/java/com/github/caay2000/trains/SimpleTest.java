package com.github.caay2000.trains;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

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
    private Train train6 = new Train(route1, 2d);

    private int screenMargin = 100;

    private World world;
    //    private Sprite sprite;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    @Override
    public void create() {

        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();

        camera = new OrthographicCamera(3000, 3080);

        world = new World.Configuration().withNumberOfCities(2000).build();
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


        draw(world);

    }

    private void draw(World world) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glDisable(GL20.GL_BLEND);

        batch.setProjectionMatrix(camera.combined);

//        batch.begin();
//        sprite.draw(batch);
//        batch.end();

        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Position p : world.getCities()) {
            shapeRenderer.circle(p.getX() + Math.abs(world.getMinX()) + screenMargin, p.getY() + Math.abs(world.getMinY()) + screenMargin, 2);
        }

        shapeRenderer.end();
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
