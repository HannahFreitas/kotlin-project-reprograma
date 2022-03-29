package br.com.projetokotlin.dto

data class UserForm(
    val email: String,
    val password: String,
    val role: List<String> = listOf("pf", "pj")
)