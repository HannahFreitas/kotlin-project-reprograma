package br.com.projetokotlin.model

import br.com.projetokotlin.dto.PersonView
import java.time.LocalDateTime

data class Post(
    var id: Long? = null,
    var title: String,
    var message: String,
    val person: PersonView,//trocar pra person
    val status: StatusPost = StatusPost.ABERTO,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)