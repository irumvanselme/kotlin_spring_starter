package com.okavaa.kotlin_spring_starter.services

import com.okavaa.kotlin_spring_starter.models.Todo
import com.okavaa.kotlin_spring_starter.repositories.ITodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl(@Autowired var iTodoRepository: ITodoRepository) : ITodoService {

    override fun all(): List<Todo> {
        return iTodoRepository.findAll();
    }

    override fun create(todo: Todo): Todo {
        return iTodoRepository.save(todo);
    }
}