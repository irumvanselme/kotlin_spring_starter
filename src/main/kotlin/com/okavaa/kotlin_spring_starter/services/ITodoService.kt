package com.okavaa.kotlin_spring_starter.services

import com.okavaa.kotlin_spring_starter.models.Todo

interface ITodoService {

    fun all(): List<Todo>

    fun create(todo: Todo): Todo
}