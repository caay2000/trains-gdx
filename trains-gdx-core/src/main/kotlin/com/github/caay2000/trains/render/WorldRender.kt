package com.github.caay2000.trains.render

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.github.caay2000.trains.world.World

class WorldRender {

    private val screenMargin = 50
    private val shapeRenderer: ShapeRenderer
    private val batch: SpriteBatch
    private val camera: OrthographicCamera
    var width = 800f
    var height: Float
    var aspectRatio: Float

    fun render(world: World) {
        camera.update()
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        Gdx.gl.glEnable(GL20.GL_BLEND)
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)
        Gdx.gl.glDisable(GL20.GL_BLEND)
        batch.projectionMatrix = camera.combined
        shapeRenderer.color = Color.BLACK
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        for (city in world.cities!!) {
            shapeRenderer.circle(
                    city.position().x + xOffset(world),
                    city.position().y + yOffset(world),
                    city.population() / 1000 + 1.toFloat())
        }
        for (route in world.getRoutes()) {
            shapeRenderer.line(route!!.start.position().x + xOffset(world),
                    route.start.position().y + yOffset(world),
                    route.end.position().x + xOffset(world),
                    route.end.position().y + yOffset(world))
        }
        shapeRenderer.color = Color.RED
        for (train in world.getTrains()) {
            shapeRenderer.circle(
                    train.position.x + xOffset(world),
                    train.position.y + yOffset(world), 2f)
        }
        shapeRenderer.end()
    }

    fun xOffset(world: World): Int {
        return Math.abs(world.size.minX) + screenMargin
    }

    fun yOffset(world: World): Int {
        return Math.abs(world.size.minY) + screenMargin
    }

    init {
        shapeRenderer = ShapeRenderer()
        batch = SpriteBatch()
        camera = OrthographicCamera()
        aspectRatio = Gdx.graphics.height.toFloat() / Gdx.graphics.width.toFloat()
        height = width * aspectRatio
        camera.position[width / 2, height / 2] = 0f
        camera.viewportWidth = width / 2
        camera.viewportHeight = height / 2
    }
}