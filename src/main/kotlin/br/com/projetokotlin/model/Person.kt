package br.com.projetokotlin.model

import br.com.projetokotlin.dto.UserView
import java.time.LocalDateTime

data class Person(
    var id: Long? = null,
    val name: String,
    val adress: String,
    val phone: String,
    val whatsapp: Boolean,
    val cpf: String,
    val user: UserView,//trocar pra user
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updateAt: LocalDateTime = LocalDateTime.now()
)