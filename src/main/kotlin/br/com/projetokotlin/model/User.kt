package br.com.projetokotlin.model

import java.time.LocalDateTime


data class User(
    var id: Long? = null,
    val email: String,
    val password: String,
    val role: List<String> = listOf("pf", "pj"),
    val createdAt: LocalDateTime = LocalDateTime.now()
)