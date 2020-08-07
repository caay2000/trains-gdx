package com.github.caay2000.trains.world.company

import com.github.caay2000.trains.CompanyColors
import com.github.caay2000.trains.Configuration
import com.github.caay2000.trains.world.`object`.entity.Entity
// import com.github.caay2000.trains.world.object.entity.Train
import com.github.caay2000.trains.world.`object`.entity.Route
import com.github.caay2000.trains.world.`object`.location.Location
import java.util.UUID

class Company(val companyColor: CompanyColors = CompanyColors.BLACK) {

    val id = UUID.randomUUID()

    private val entities: MutableCollection<Entity> = mutableSetOf()
    private val routes: MutableCollection<Route> = mutableSetOf()
    val connectedCities: MutableCollection<Location> = mutableSetOf()

    private val newRouteStrategy = NewRouteStrategy(this)

    val balance: Balance = Balance(this)

    fun addRoute(route: Route) {
        this.routes.add(route)
        this.connectedCities.addAll(route.cities)
    }

    fun addEntity(entity: Entity) {
        this.entities.add(entity)
    }

    fun routes() = this.routes
    fun entities() = this.entities

    fun update(delta: Float) {
        updateCompanyAssets(delta)
        updateEntities(delta)
    }

    private fun updateCompanyAssets(delta: Float) {
        if (this.balance > Configuration.companyMinBalanceForNewRoute.toMoney()) {
            this.newRouteStrategy.createNewRoute()
        }
    }

    private fun updateEntities(delta: Float) {
        this.entities.forEach { e -> e.update(delta) }
    }
}