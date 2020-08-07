package com.github.caay2000.trains.world.company

import com.github.caay2000.trains.Configuration

class Balance(private val company: Company) {

    var balance: Money = Configuration.companyStartingBalance.toMoney()
        private set

    fun income(amount: Money) {
        this.balance = balance.plus(amount)
        // info { "Company ${this.company.id} income $amount. current balance = ${this.balance}" }
    }

    fun expense(amount: Money) {
        this.balance = balance.minus(amount)
        // info { "Company ${this.company.id} expensed $amount. current balance = ${this.balance}" }
    }

    operator fun compareTo(toMoney: Money): Int =
        this.balance.value.compareTo(toMoney.value)
}
