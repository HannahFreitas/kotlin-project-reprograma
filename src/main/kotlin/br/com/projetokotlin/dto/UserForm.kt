package br.com.projetokotlin.dto

import org.hibernate.validator.constraints.UniqueElements
import javax.validation.constraints.NotEmpty

data class UserForm(
    @field:NotEmpty
    @UniqueElements
    val email: String,
    @field:NotEmpty
    val password: String,
    @NotEmpty
    val role: List<String> = listOf("pf", "pj")
)