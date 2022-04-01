package br.com.projetokotlin.dto

import java.time.LocalDateTime

data class UserView(
    val id: Long?,
    val email: String,
    val password: String,
    val role: List<String>,
    val createdAt: LocalDateTime
)
