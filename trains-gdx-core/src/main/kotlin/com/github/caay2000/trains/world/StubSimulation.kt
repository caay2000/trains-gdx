package com.github.caay2000.trains.world

import com.github.caay2000.trains.world.entity.Train
import com.github.caay2000.trains.world.entity.Wagon
import com.github.caay2000.trains.world.entity.WagonType

object StubSimulation {

    fun stubSimulationUpdate(world: World) {
        if (world.companies.isEmpty()) {
            world.addCompany(Company())
        }

        try {
            val start = if (world.cities.filter { e -> e.connected() }.none()) world.cities.random()
            else world.cities.filter { e -> e.connected() && e.citiesInRange.any { i -> !i.connected() } }.random()

            val end = start.citiesInRange.filter { e -> !e.connected() }.random()
            val route = Route(start, end)
            world.companies.random().addRoute(route)
            start.addConnection(end)
            end.addConnection(start)
            world.companies.random().addEntity(Train((200..300).random().toFloat(), route, listOf<Wagon>(Wagon(WagonType.DEFAULT_PAX))))
        } catch (e: Exception) {

        }
    }
}