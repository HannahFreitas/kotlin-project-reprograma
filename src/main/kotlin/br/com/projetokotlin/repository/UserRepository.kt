package br.com.projetokotlin.repository

import br.com.projetokotlin.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
}