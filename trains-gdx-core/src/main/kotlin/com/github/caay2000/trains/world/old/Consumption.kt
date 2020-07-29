// package com.github.caay2000.trains.world.object.location
//
// import com.github.caay2000.trains.Configuration
// import com.github.caay2000.trains.debug
// import com.github.caay2000.trains.world.object.entity.CargoType
// import com.github.caay2000.trains.world.object.entity.Wagon
//
// internal class Consumption {
//
//     private val city: City
//     private val consumed: Map<CargoType, Int> = mutableMapOf()
//
//     constructor(city: City) {
//         this.city = city
//     }
//
//     private fun put(type: CargoType, units: Int) {
//         (consumed as MutableMap)[type] = units
//     }
//
//     fun growPopulation(): Int {
//         var total = 0
//         val maxDelivered = (city.size * Configuration.cityDeliveredCargoPopulationGrowRate).toInt()
//         for (cargoType in consumed.keys) {
//             val value = consumed.getValue(cargoType)
//             if (value.toDouble() > maxDelivered) {
//                 val division = value.toDouble().div(maxDelivered)
//                 total += division.toInt()
//                 this.put(cargoType, value - maxDelivered * division.toInt())
//             }
//         }
//         debug(total > 0) { "DELIVERED cargo increases ${city.name}[${city.id}] population by $total" }
//         return total
//     }
//
//     fun unload(wagon: Wagon) {
//         val actualConsumed = consumed.getOrDefault(wagon.model.cargoType, 0)
//         this.put(wagon.model.cargoType, actualConsumed + wagon.load)
//         debug(wagon.load > 0) { "UNLOAD ${wagon.load} ${wagon.model.cargoType} of ${consumed[wagon.model.cargoType]} at ${city.name}[${city.id}]" }
//         wagon.load = 0
//     }
// }
