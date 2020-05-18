package com.github.caay2000.trains.world.entity

import com.github.caay2000.trains.DelayedCounter
import com.github.caay2000.trains.world.CargoType
import com.github.caay2000.trains.world.Position
import com.github.caay2000.trains.world.Route

class Train(private val speed: Float, private val route: Route, private var wagons: List<Wagon>) : Entity {

    private val eventHandler = TrainEventHandler()

    override val position: Position = Position(route.start.position)
    private var status: EntityStatus = EntityStatus.ON_ROUTE

    override fun update(delta: Float) {
        eventHandler.handle(this, delta)
    }

    private fun move(delta: Float): Float {

        val missing = (this.speed * delta) - position.distanceTo(route.end.position)
        return if (missing <= 0) {
            position.move(route.end.position, speed * delta)
            0f
        } else {
            position.translate(route.end.position)
            delta * missing / (speed * delta)
        }
    }

    private fun unloadWagons() {
        // val demands = this.route.end.consumes
        // for(wagon in wagons){
        //     (this.route.end as City).deliverCargo(wagon.load)
        //     wagon.load = 0
        // }
    }

    private fun loadWagons(loadCargos: Set<CargoType>) {

        // val offers: Map<CargoType, Cargo> = this.route.end.produces
        // for (wagon in wagons) {
        //     if (loadCargos.contains(wagon.type.type)) {
        //         val cargo = offers.getOrDefault(wagon.type.type, Cargo(wagon.type.type))
        //         if (cargo.quantity > 0) {
        //             wagon.load = min(cargo.quantity, wagon.type.capacity)
        //
        //             offers[wagon.type.type]?.quantity = (offers[wagon.type.type]?.quantity ?: 0) - wagon.load
        //
        //             debug { "wagon loaded with ${wagon.load} ${wagon.type}. Station ${(this.route.end as City).name} has now ${offers.get(wagon.type.type)?.quantity ?: 0} ${wagon.type}" }
        //         }
        //     }
        // }
    }

    enum class EntityStatus {
        ON_ROUTE,
        STATION_STOP,
        UNLOADING,
        LOADING
    }

    private class TrainEventHandler {

        private val stationStopDelay = DelayedCounter(1f)

        fun handle(train: Train, delta: Float) {
            if (delta > 0) {
                when (train.status) {
                    EntityStatus.ON_ROUTE -> {
                        val result = train.move(delta)
                        if (result > 0) {
                            train.status = EntityStatus.STATION_STOP
                            this.handle(train, result)
                        }
                    }
                    EntityStatus.STATION_STOP -> {
                        val result = stationStopDelay.update(delta)
                        if (result > 0) {
                            train.status = EntityStatus.UNLOADING
                            this.handle(train, result)
                        }
                    }
                    EntityStatus.UNLOADING -> {
                        // train.unloadWagons()
                        train.status = EntityStatus.LOADING
                        this.handle(train, delta)
                    }
                    EntityStatus.LOADING -> {
                        // val loadCargos = train.wagons
                        //     .map { e -> e.type.type }
                        //     .filter { e -> train.route.nextStop().consumes.contains(e) }
                        //     .toSet()
                        // train.loadWagons(loadCargos)
                        train.route.endRoute()
                        train.status = EntityStatus.ON_ROUTE
                        this.handle(train, delta)
                    }
                }
            }
        }
    }
}