package br.com.projetokotlin.model

import br.com.projetokotlin.dto.PersonView
import java.time.LocalDateTime

data class Post(
    var id: Long? = null,
    val title: String,
    val message: String,
    val person: PersonView,
    val status: StatusPost = StatusPost.ABERTO,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = null
)