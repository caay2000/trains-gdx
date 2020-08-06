// package com.github.caay2000.trains.world.object.location
//
// import com.github.caay2000.trains.Configuration
// import com.github.caay2000.trains.debug
// import com.github.caay2000.trains.world.object.entity.CargoType
// import com.github.caay2000.trains.world.object.entity.Entity
// import com.github.caay2000.trains.world.object.entity.Wagon
// import kotlin.math.max
// import kotlin.math.min
//
// internal class Production {
//
//     private val city: Location
//     private val produced: Map<CargoType, Int> = mutableMapOf()
//
//     private var paxDelta: Float = 0f
//     private var mailDelta: Float = 0f
//
//     constructor(city: City) {
//         this.city = city
//     }
//
//     private fun put(type: CargoType, units: Int) {
//         (produced as MutableMap)[type] = units
//     }
//
//     fun update(delta: Float) {
//
//         val cargoTypesSet = getProductionCargos()
//         for (cargoType in cargoTypesSet) {
//             when (cargoType) {
//                 CargoType.PAX -> paxProduction(delta)
//                 CargoType.MAIL -> mailProduction(delta)
//             }
//         }
//     }
//
//     private fun mailProduction(delta: Float) {
//         if (city.connected()) {
//             val value = produced.getOrElse(CargoType.MAIL) { 0 }
//             this.mailDelta += city.size * delta * Configuration.cityRatioPopulationMail
//             val grow = mailDelta.toInt()
//             if (grow > 0) {
//                 this.put(CargoType.MAIL, value + grow)
//                 this.mailDelta -= grow
//                 debug(grow > 0) { "PRODUCTION MAIL ${city.name}[${city.id}] increased by $grow to ${value + grow}" }
//             }
//         }
//     }
//
//     private fun paxProduction(delta: Float) {
//         if (city.connected()) {
//             val value = produced.getOrElse(CargoType.PAX) { 0 }
//             if (value < city.size * Configuration.cityMaxRatioPAXPopulation) {
//                 this.paxDelta += city.size * delta * Configuration.cityRatioPopulationPAX
//                 val grow = paxDelta.toInt()
//                 if (grow > 0) {
//                     this.put(CargoType.PAX, value + grow)
//                     this.paxDelta -= grow
//                     debug(grow > 0) { "PRODUCTION PAX ${city.name}[${city.id}] increased by $grow to ${value + grow}" }
//                 }
//             }
//         }
//     }
//
//     private fun getProductionCargos(): Set<CargoType> = setOf(CargoType.PAX, CargoType.MAIL)
//
//     fun load(entity: Entity, delta: Float) {
//         for (wagon in entity.wagons) {
//             loadWagon(wagon, delta)
//         }
//     }
//
//     private fun loadWagon(wagon: Wagon, delta: Float) {
//         if (this.produced.containsKey(wagon.model.cargoType)) {
//             val produced = this.produced.getValue(wagon.model.cargoType)
//             val maxLoadable = ((wagon.model.capacity) * delta / wagon.model.loadTimeUnit).toInt()
//             wagon.load = min(maxLoadable, produced)
//             this.put(wagon.model.cargoType, max(produced - maxLoadable, 0))
//             debug(wagon.load > 0) { "LOAD ${wagon.load} ${wagon.model.cargoType} at ${city.name}[${city.id}]" }
//         }
//     }
// }