package br.com.projetokotlin.dto

import java.time.LocalDateTime

data class PersonView(
    var id: Long?,
    val name: String,
    val createdAt: LocalDateTime,
    val updateAt: LocalDateTime?
)