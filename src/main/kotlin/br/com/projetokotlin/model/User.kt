package br.com.projetokotlin.model



import java.time.LocalDateTime


data class User(
    var id: Long? = null,
    var email: String = "",
    var password: String = "",
    val role: RolesStatus,
    val createdAt: LocalDateTime = LocalDateTime.now()
)