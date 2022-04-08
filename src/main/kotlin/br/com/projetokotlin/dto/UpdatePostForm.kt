package br.com.projetokotlin.dto

import br.com.projetokotlin.model.StatusPost
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class UpdatePostForm(
    @field:NotEmpty
    val title: String,
    @field:NotEmpty
    val message: String,
    val status: StatusPost,
    val updatedAt: LocalDateTime = LocalDateTime.now()
)