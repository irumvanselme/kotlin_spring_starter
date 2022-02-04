package com.okavaa.kotlin_spring_starter.utils.payload

import lombok.AllArgsConstructor
import lombok.Data
import org.springframework.web.bind.annotation.ControllerAdvice
import java.lang.RuntimeException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.validation.FieldError
import org.springframework.http.HttpStatus
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
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