package com.okavaa.kotlin_spring_starter.models

import com.okavaa.kotlin_spring_starter.audits.Model
import javax.persistence.*

@Entity
@Table(name = "todos")
open class Todo(
    @Column(name = "title") var title: String, @Column(name = "description") var description: String
) : Model() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}
