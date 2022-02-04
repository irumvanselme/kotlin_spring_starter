package com.okavaa.kotlin_spring_starter.audits

import javax.persistence.EntityListeners
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.CreationTimestamp
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import org.hibernate.annotations.UpdateTimestamp
import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@JsonIgnoreProperties(value = ["createdAt", "updatedAt"], allowGetters = true)
open class Model {

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at")
    private val createdAt: LocalDateTime? = null

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_at")
    private val updatedAt: LocalDateTime? = null

}