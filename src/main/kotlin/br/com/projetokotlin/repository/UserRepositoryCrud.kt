package br.com.projetokotlin.repository

import br.com.projetokotlin.model.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepositoryCrud: CrudRepository<User, Long> {

    @Query("SELECT u FROM User u where u.email = ?1")
    fun findUserByLogin(email: String): User
}