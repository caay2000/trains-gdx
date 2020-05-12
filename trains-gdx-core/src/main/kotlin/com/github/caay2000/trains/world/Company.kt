package com.github.caay2000.trains.world

class Company {

    private val entities: MutableCollection<Entity>
    private val routes: MutableCollection<Route>

    constructor() {
        this.entities = mutableSetOf()
        this.routes = mutableSetOf()
    }

    fun addRoute(route: Route) {
        this.routes.add(route)
    }

    fun addEntity(entity: Entity) {
        this.entities.add(entity)
    }

    fun routes() = this.routes
    fun entities() = this.entities

    fun update(delta: Float) {
        this.entities.forEach { e -> e.update(delta) }
    }
}