package com.github.caay2000.trains.render

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.github.caay2000.trains.world.GlobalData
import com.github.caay2000.trains.world.World
import kotlin.math.abs

class WorldRender {

    private val screenMargin = 50
    private val shapeRenderer: ShapeRenderer = ShapeRenderer()
    private val batch: SpriteBatch = SpriteBatch()
    private var width = 800f
    private var height: Float
    private var aspectRatio: Float = Gdx.graphics.height.toFloat() / Gdx.graphics.width.toFloat()
    private val camera: OrthographicCamera = OrthographicCamera(1f, aspectRatio)
    private var worldPopulation = 0
    private var font = BitmapFont()

    fun render(world: World) {
        // debug { "Update delta: ${GlobalData.delta}, elapsed: ${GlobalData.current}" }
        worldPopulation = 0
        camera.update()
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        Gdx.gl.glEnable(GL20.GL_BLEND)
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)
        Gdx.gl.glDisable(GL20.GL_BLEND)
        batch.projectionMatrix = camera.combined

        shapeRenderer.color = Color.BLACK
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        for (city in world.cities()) {
            shapeRenderer.circle(
                city.position.x + xOffset(world),
                city.position.y + yOffset(world),
                city.size / 1000 + 1.toFloat()
            )
            worldPopulation += city.size
        }
        for (company in world.companies()) {
            shapeRenderer.color = company.companyColor.color
            for (route in company.routes()) {
                for (i in route.cities.withIndex())
                    if (i.index != 0) {
                        shapeRenderer.line(
                            route.cities[i.index - 1].position.x + xOffset(world),
                            route.cities[i.index - 1].position.y + yOffset(world),
                            route.cities[i.index].position.x + xOffset(world),
                            route.cities[i.index].position.y + yOffset(world)
                        )
                    }
            }
            shapeRenderer.color = Color.RED
            for (train in company.entities()) {
                shapeRenderer.circle(
                    train.position.x + xOffset(world),
                    train.position.y + yOffset(world), 2f
                )
            }
        }

        shapeRenderer.end()

        batch.begin()
        font.draw(batch, "population $worldPopulation", 400f, 400f)
        font.draw(batch, "elapsed ${elapsedString(GlobalData.current)}", 400f, 420f)
        batch.end()
    }

    private fun elapsedString(current: Float): String {
        return "${current.toInt() / 60}:${current.toInt() % 60}"
    }

    private fun xOffset(world: World): Int {
        return abs(world.minX()) + screenMargin
    }

    private fun yOffset(world: World): Int {
        return abs(world.minY()) + screenMargin
    }

    init {
        height = width * aspectRatio
        camera.position[width / 2, height / 2] = 0f
        camera.viewportWidth = width / 2
        camera.viewportHeight = height / 2
    }
}