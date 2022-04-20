package br.com.projetokotlin.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class PostForm(
    @field:NotEmpty
    val title: String? = null,
    @field:NotEmpty
    val message: String? = null,
    @field:NotNull
    val person_id: Long? = null
)