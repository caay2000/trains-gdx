package com.github.caay2000.trains.world.company

import com.github.caay2000.trains.Configuration
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Balance(private val company: Company) {

    private val logger: Logger = LoggerFactory.getLogger(Balance::class.java)

    var balance: Money = Configuration.companyStartingBalance.toMoney()
        private set

    fun income(amount: Money) {
        this.balance = balance.plus(amount)
        logger.info("Company ${this.company.id} income $amount. current balance = ${this.balance}")
    }

    fun expense(amount: Money) {
        this.balance = balance.minus(amount)
        logger.info("Company ${this.company.id} expensed $amount. current balance = ${this.balance}")
    }

    operator fun compareTo(toMoney: Money): Int =
        this.balance.value.compareTo(toMoney.value)
}
