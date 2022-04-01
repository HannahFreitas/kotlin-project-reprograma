package br.com.projetokotlin.model


import java.time.LocalDateTime


data class User(
    var id: Long? = null,
    var email: String = "",
    var password: String = "",
    var role: List<String> = mutableListOf("pf", "pj"),
    val createdAt: LocalDateTime = LocalDateTime.now()

)