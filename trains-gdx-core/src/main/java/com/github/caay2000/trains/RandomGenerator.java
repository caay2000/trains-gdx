package com.github.caay2000.trains;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {

    ThreadLocalRandom localRandom = ThreadLocalRandom.current();

    public int nextInt(int min, int max) {
        return localRandom.nextInt(0, Math.abs(max) + Math.abs(min) + 1) - Math.abs(min);
    }

}
