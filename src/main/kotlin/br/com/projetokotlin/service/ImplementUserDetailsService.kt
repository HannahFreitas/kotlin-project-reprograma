package br.com.projetokotlin.service

import br.com.projetokotlin.model.User
import br.com.projetokotlin.repository.UserRepositoryCrud
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class ImplementUserDetailsService(private val userRepositoryCrud: UserRepositoryCrud, private val userService: UserService): UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userService.findByEmail(email)
        return org.springframework.security.core.userdetails.User(user.email, user.password, ArrayList())
    }

}