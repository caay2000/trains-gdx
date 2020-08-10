package com.github.caay2000.trains.world.company

import com.github.caay2000.trains.world.GlobalData
import com.github.caay2000.trains.world.`object`.entity.BasicWagonType
import com.github.caay2000.trains.world.`object`.entity.Entity
import com.github.caay2000.trains.world.`object`.entity.Route
import com.github.caay2000.trains.world.`object`.entity.Wagon
import com.github.caay2000.trains.world.`object`.location.Location

class NewRouteStrategy(private val company: Company) {

    private val routeCost: Money = 8000.toMoney()

    private val wagonList = listOf(Wagon(BasicWagonType.DEFAULT_PAX), Wagon(BasicWagonType.DEFAULT_PAX), Wagon(BasicWagonType.DEFAULT_MAIL))

    fun createNewRoute() {
        val startLocation = randomLocation()
        val possibleRoutes = startLocation.locationsInRange
            .subtract(this.company.connectedCities)
            .filter { e -> !e.connections.contains(startLocation) }
        if (possibleRoutes.isNotEmpty()) {
            val selectedLocation = possibleRoutes.random()
            val route = Route(startLocation, selectedLocation)
            this.company.addRoute(route)
            this.company.addEntity(Entity(this.company, (6..10).random().toFloat(), route, wagonList))
            this.company.balance.expense(routeCost)
        }
    }

    private fun randomLocation(): Location {
        return if (this.company.connectedCities.isEmpty()) {
            GlobalData.world.locations.random()
        } else {
            this.company.connectedCities.random()
        }
    }
}