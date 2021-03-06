package com.github.caay2000.trains

// import com.github.caay2000.trains.world.object.entity.TrainEventHandler
// import com.github.caay2000.trains.world.object.entity.TrainEventProcessor
import com.github.caay2000.trains.render.WorldRender
import com.github.caay2000.trains.world.GlobalData
import com.github.caay2000.trains.world.World
import com.github.caay2000.trains.world.company.Company
import com.github.caay2000.trains.world.generator.WorldGenerator
import ktx.app.KtxApplicationAdapter

class SimpleTest : KtxApplicationAdapter {

    private lateinit var world: World
    private lateinit var worldRender: WorldRender

    override fun create() {

        Configuration.numberOfCities = 30

        // TrainEventHandler(TrainEventProcessor())
        //Gdx.app.logLevel = Application.LOG_DEBUG

        world = WorldGenerator.generate()
        worldRender = WorldRender()
        for (i in 1..Configuration.numberOfCompanies) {
            world.addCompany(Company(CompanyColors.valueOf(i)))
        }

        GlobalData.world = world
    }

    override fun resize(i: Int, i1: Int) {}
    override fun render() {

        GlobalData.update()



        world.update()
        worldRender.render(world)
    }

    override fun pause() {}
    override fun resume() {}
    override fun dispose() {}
}