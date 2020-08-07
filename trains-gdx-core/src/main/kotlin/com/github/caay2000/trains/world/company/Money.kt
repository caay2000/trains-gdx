package com.github.caay2000.trains.world.company

import java.math.BigDecimal
import java.math.RoundingMode

private const val SCALE = 2
private val ROUNDING = RoundingMode.HALF_EVEN

data class Money private constructor(val value: BigDecimal) {

    operator fun plus(money: Money): Money =
        build(value + money.value)

    operator fun minus(money: Money): Money =
        build(value - money.value)

    operator fun times(multiplier: BigDecimal): Money =
        build(value * multiplier)

    operator fun div(divider: BigDecimal): Money {
        require(divider != BigDecimal.ZERO) { "Cannot divide Money value by zero" }
        return build(value / divider)
    }

    operator fun compareTo(other: Money): Int =
        value.compareTo(other.value)

    fun min(other: Money): Money = value.min(other.value).toMoney()

    fun isNotEmpty(): Boolean = value > BigDecimal.ZERO

    companion object {
        @JvmField
        val ZERO = build(BigDecimal.ZERO)
        val FIVE = build(BigDecimal.valueOf(5))
        val TEN = build(BigDecimal.TEN)

        internal fun build(value: BigDecimal): Money {
            val scaled = value.setScale(
                SCALE,
                ROUNDING
            )
            return Money(scaled)
        }
    }
}

fun BigDecimal.toMoney() = Money.build(this)
fun Int.toMoney() = Money.build(this.toBigDecimal())

// fun Collection<Money>.sum(): Money = fold(Money.ZERO, Money::plus)
