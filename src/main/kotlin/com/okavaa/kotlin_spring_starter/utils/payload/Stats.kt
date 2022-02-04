package com.okavaa.kotlin_spring_starter.utils.payload

class Stats {
    var incomes: Long? = null
    var incomesSum: Double? = null
    var expenses: Long? = null
    var expensesSum: Double? = null
    val totalTransactions: Long
        get() = incomes!! + expenses!!
    val totalTransactionsSum: Double
        get() = incomesSum!! + expensesSum!!
    val profit: Double
        get() = incomesSum!! - expensesSum!!
}