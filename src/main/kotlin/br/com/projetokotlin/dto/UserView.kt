package br.com.projetokotlin.dto


import br.com.projetokotlin.model.RolesStatus
import java.time.LocalDateTime


data class UserView(
    val id: Long?,
    val email: String,
    val role: RolesStatus,
    val createdAt: LocalDateTime
)
