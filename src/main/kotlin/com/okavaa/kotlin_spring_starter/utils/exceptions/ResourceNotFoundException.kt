package com.okavaa.kotlin_spring_starter.utils.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException(resourceName: String?, fieldName: String?, fieldValue: String?) : RuntimeException(
    String.format("%s with %s ['%s'] not found", resourceName, fieldName, fieldValue)
)