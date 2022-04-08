package br.com.projetokotlin.dto

import br.com.projetokotlin.model.StatusPost
import java.time.LocalDateTime

data class PostView(
    var id: Long?,
    val title: String,
    val message: String,
    val person: PersonView,
    val status: StatusPost,
    val createdAt: LocalDateTime,
    val uptadedAt: LocalDateTime?
)