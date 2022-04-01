package br.com.projetokotlin.dto

import org.hibernate.validator.constraints.UniqueElements
import javax.validation.constraints.NotEmpty

data class LoginForm(
    @field:NotEmpty(message = "Email nao encontrado!")
    val email: String,
    @field:NotEmpty(message = "Senha digitada incorretamente!")
    val password: String
)