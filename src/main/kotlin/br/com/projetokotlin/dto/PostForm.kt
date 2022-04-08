package br.com.projetokotlin.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class PostForm(
    @field:NotEmpty
    val title: String,
    @field:NotEmpty
    val message: String,
    @field:NotNull
    val idPerson: Long
)