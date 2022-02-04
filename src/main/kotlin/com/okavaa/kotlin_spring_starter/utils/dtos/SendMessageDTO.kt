package com.okavaa.kotlin_spring_starter.utils.dtos

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class SendMessageDTO {
    private val senderId: @NotNull Long? = null
    private val receiverId: @NotNull Long? = null
    private val body: @NotEmpty String? = null
}