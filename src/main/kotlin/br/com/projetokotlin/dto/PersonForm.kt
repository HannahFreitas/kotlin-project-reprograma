package br.com.projetokotlin.dto


import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class PersonForm(
    @field:NotEmpty(message = "Nome está vazio!")
    val name: String,
    @field:NotEmpty(message = "Informe o seu endereço.")
    val adress: String,
    @field:NotEmpty(message = "Informe o número do seu telefone!")
    val phone: String,
    @NotNull(message = "Informe se o seu telefone é whatsapp.")
    val whatsapp: Boolean,
    @NotEmpty(message = "Cpf vazio!")
    val cpf: String,
    @NotNull(message = "Informe a identificaçao do seu usuário no sistema!")
    val idUser: Long
)