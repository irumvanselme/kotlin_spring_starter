package com.okavaa.kotlin_spring_starter.repositories

import com.okavaa.kotlin_spring_starter.models.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface ITodoRepository : JpaRepository<Todo, Long> {

}