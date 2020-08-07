package com.github.caay2000.trains.world.`object`.entity

import com.github.caay2000.trains.world.`object`.WorldObject
import com.github.caay2000.trains.world.`object`.entity.Entity.EntityStatus.EntityStatusValue
import com.github.caay2000.trains.world.`object`.entity.strategy.LoadStrategy
import com.github.caay2000.trains.world.`object`.entity.strategy.MoveStrategy
import com.github.caay2000.trains.world.`object`.entity.strategy.UnloadStrategy
import com.github.caay2000.trains.world.company.Company
import com.github.caay2000.trains.world.position.Position
import java.util.UUID

class Entity : WorldObject {

    override val id: UUID = UUID.randomUUID()

    val company: Company
    val maxSpeed: Float
    val route: Route
    val position: Position
    val wagons: List<Wagon>

    private var status = EntityStatus()
    private val moveStrategy = MoveStrategy(this)
    private val loadStrategy = LoadStrategy(this)
    private val unloadStrategy = UnloadStrategy(this)

    constructor(company: Company, maxSpeed: Float, route: Route, wagons: List<Wagon>) {
        this.company = company
        this.maxSpeed = maxSpeed
        this.route = route
        this.wagons = wagons
        this.position = Position(route.currentStop().position)
    }

    override fun update(delta: Float) {

        val diffDelta = when (status.value) {
            EntityStatusValue.MOVING -> moveStrategy.update(delta)
            EntityStatusValue.UNLOADING -> unloadStrategy.update(delta)
            EntityStatusValue.LOADING -> {
                loadStrategy.update(delta).also { updateRoute(it) }
            }
        }
        if (diffDelta > 0F) {
            status.next()
            this.update(diffDelta)
        }
    }

    private fun updateRoute(it: Float) {
        if (it != 0F) {
            this.route.update()
        }
    }

    override fun toString(): String {
        return "Entity(id=$id)"
    }

    class EntityStatus {

        var value: EntityStatusValue = EntityStatusValue.LOADING
            private set

        fun next() {
            this.value = this.value.next()
        }

        enum class EntityStatusValue {

            MOVING {
                override fun next(): EntityStatusValue = UNLOADING
            },
            UNLOADING {
                override fun next(): EntityStatusValue = LOADING
            },
            LOADING {
                override fun next(): EntityStatusValue = MOVING
            };

            abstract fun next(): EntityStatusValue
        }
    }
}