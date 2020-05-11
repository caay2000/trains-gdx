package com.github.caay2000.trains.util

import java.util.concurrent.ThreadLocalRandom

object RandomGenerator {
    private val localRandom = ThreadLocalRandom.current()
    fun randomPositiveInt(min: Int, max: Int): Int {
        return localRandom.nextInt(min, max + 1)
    }

    fun randomSignedInt(min: Int, max: Int): Int {
        return localRandom.nextInt(0, Math.abs(max) + Math.abs(min) + 1) - Math.abs(min)
    }

    fun <T> randomItem(collection: Collection<T>): T {
        val random = randomPositiveInt(0, collection.size - 1)


        return return collection.shuffled().take(1)[0];

    }
}