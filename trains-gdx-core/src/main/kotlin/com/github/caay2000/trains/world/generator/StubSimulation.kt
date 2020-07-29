package com.github.caay2000.trains.world.generator

import com.github.caay2000.trains.world.company.Company
import com.github.caay2000.trains.world.World
import com.github.caay2000.trains.world.`object`.entity.Entity
import com.github.caay2000.trains.world.`object`.entity.Route
import com.github.caay2000.trains.world.`object`.entity.Wagon
import com.github.caay2000.trains.world.`object`.entity.BasicWagonType

object StubSimulation {

    var simulationCompleted: Boolean = false

    fun stubSimulationUpdate(world: World) {
        if (!simulationCompleted) {
            if (world.companies.isEmpty()) {
                world.addCompany(Company())
            }

            try {

                val wagonList = listOf(Wagon(BasicWagonType.DEFAULT_PAX), Wagon(BasicWagonType.DEFAULT_PAX), Wagon(BasicWagonType.DEFAULT_MAIL))

                val start = if (world.locations.filter { e -> e.connected() }.none()) world.locations.random()
                else world.locations.filter { e -> e.connected() && e.locationsInRange.any { i -> !i.connected() } }.random()

                val end = start.locationsInRange.filter { e -> !e.connected() }.random()
                val route = Route(start, end)
                world.companies.random().addRoute(route)
                start.addConnection(end)
                end.addConnection(start)
                world.companies.random().addEntity(Entity((6..10).random().toFloat(), route, wagonList))
            } catch (e: Exception) {

            }
            simulationCompleted = world.locations.all { e -> e.connected() }
        }
    }
}