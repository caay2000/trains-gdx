package com.github.caay2000.trains

import com.github.caay2000.trains.world.position.Position
import org.assertj.core.api.Assertions
import org.junit.Test

class PositionTest {
    @Test
    fun `distance to`() {
        val sut = Position()
        val result = sut.distanceTo(Position(10, 0))
        Assertions.assertThat(result).isEqualTo(10f)
    }

    @Test
    fun `move to`() {
        val sut = Position()
        sut.move(Position(10, 0), 10f)
        Assertions.assertThat(sut).isEqualTo(Position(10, 0))
    }

    @Test
    fun `move to less distance`() {
        val sut = Position()
        sut.move(Position(10, 0), 5f)
        Assertions.assertThat(sut).isEqualTo(Position(5, 0))
    }

    @Test
    fun `move to more distance`() {
        val sut = Position()
        sut.move(Position(10, 0), 15f)
        Assertions.assertThat(sut).isEqualTo(Position(10, 0))
    }



    // @Test
    // public void moveToLessDistance() {
    //
    //     Position sut = new Position();
    //
    //     Position result = sut.move(new Position(1, 0), 0.99f);
    //
    //     assertThat(result).isEqualTo(new Position(0.99f, 0));
    // }
    //
    // @Test
    // public void moveToMoreDistance() {
    //
    //     Position sut = new Position();
    //
    //     Position result = sut.move(new Position(1, 0), 2);
    //
    //     assertThat(result).isEqualTo(new Position(1, 0));
    // }
    //
    // @Test
    // public void moveToNegative() {
    //
    //     Position sut = new Position();
    //
    //     Position result = sut.move(new Position(0, -10), 10);
    //
    //     assertThat(result).isEqualTo(new Position(0, -10));
    // }
}