package com.github.caay2000.trains.world

import com.github.caay2000.trains.world.entity.Train

object StubSimulation {

    fun stubSimulationUpdate(world: World) {
        if (world.companies.isEmpty()) {
            world.addCompany(Company())
        }

        try {
            val start = if (world.locations.filter { e -> e.connected() }.none()) world.locations.random()
            else world.locations.filter { e -> e.connected() && e.locationsInRange.any { i -> !i.connected() } }.random()

            val end = start.locationsInRange.filter { e -> !e.connected() }.random()
            val route = Route(start, end)
            world.companies.random().addRoute(route)
            start.addConnection(end)
            end.addConnection(start)
            world.companies.random().addEntity(Train((5..12).random().toFloat(), route))
        } catch (e: Exception) {

        }
    }
}