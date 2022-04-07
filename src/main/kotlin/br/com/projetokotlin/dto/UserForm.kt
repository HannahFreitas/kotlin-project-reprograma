package br.com.projetokotlin.dto


import br.com.projetokotlin.model.RolesStatus
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size


data class UserForm(
    @field:NotEmpty(message = "Email nao pode estar vazio.")
    val email: String,
    @field:NotEmpty(message = "Senha em branco, digite sua senha!")
    @field:Size(min = 8, max = 20, message = "O minimo de caracteres sao 8 e o máximo 20 caracteres.")
    val password: String,
    @NotEmpty(message = "Informe se é uma pessoa física ou jurídica!")
    val role: RolesStatus
)