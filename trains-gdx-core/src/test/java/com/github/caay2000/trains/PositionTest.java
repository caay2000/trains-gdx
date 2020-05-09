package com.github.caay2000.trains;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {

    @Test
    public void distanceTo() {

        Position sut = new Position();

        float result = sut.distanceTo(new Position(10, 0));

        assertThat(result).isEqualTo(10f);
    }

    @Test
    public void moveTo() {

        Position sut = new Position();

        Position result = sut.move(new Position(10, 0), 10);

        assertThat(result).isEqualTo(new Position(10, 0));
    }

    @Test
    public void moveToLessDistance() {

        Position sut = new Position();

        Position result = sut.move(new Position(1, 0), 0.99f);

        assertThat(result).isEqualTo(new Position(0.99f, 0));
    }

    @Test
    public void moveToMoreDistance() {

        Position sut = new Position();

        Position result = sut.move(new Position(1, 0), 2);

        assertThat(result).isEqualTo(new Position(1, 0));
    }

    @Test
    public void moveToNegative() {

        Position sut = new Position();

        Position result = sut.move(new Position(0, -10), 10);

        assertThat(result).isEqualTo(new Position(0, -10));
    }


}