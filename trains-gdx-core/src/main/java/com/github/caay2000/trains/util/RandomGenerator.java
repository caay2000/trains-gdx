package com.github.caay2000.trains.util;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {

    private static final ThreadLocalRandom localRandom = ThreadLocalRandom.current();

    private RandomGenerator() {
    }

    public static int randomPositiveInt(int min, int max) {
        return localRandom.nextInt(min, max + 1);
    }

    public static int randomSignedInt(int min, int max) {
        return localRandom.nextInt(0, Math.abs(max) + Math.abs(min) + 1) - Math.abs(min);
    }

    public static <T> T randomItem(Collection<T> collection) {

        int random = randomPositiveInt(0, collection.size() - 1);
        return collection.stream().skip(random).findFirst().get();
    }
}
