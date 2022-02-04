package com.okavaa.kotlin_spring_starter.utils.dtos

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class SendMoneyDTO {
    val accountNumber: @NotEmpty String? = null
    val amount: @NotNull @Positive Double? = null
}