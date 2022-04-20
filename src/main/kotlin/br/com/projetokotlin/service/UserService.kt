package br.com.projetokotlin.service

import br.com.projetokotlin.dto.UserForm
import br.com.projetokotlin.dto.UserView
import br.com.projetokotlin.exception.NotFoundException
import br.com.projetokotlin.mapper.UserFormMapper
import br.com.projetokotlin.mapper.UserViewMapper
import br.com.projetokotlin.model.User
import br.com.projetokotlin.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserService(private val usersRepository: UserRepository,
                  private val notFoundMessage: String = "Usuario nao cadastrado.",
                  private val userFormMapper: UserFormMapper,
                  private val userViewMapper: UserViewMapper
) {
    fun save(userForm: UserForm): UserView {
        val passwordEnconder = BCryptPasswordEncoder()
        val user = userFormMapper.map(userForm)
        user.password = passwordEnconder.encode(userForm.password)
        usersRepository.save(user)
        return userViewMapper.map(user)
    }

    fun findById(id: Long): User? {
        return usersRepository.findById(id).orElseThrow{ NotFoundException(notFoundMessage) }
    }


    fun findByEmail(email: String): User {
        return usersRepository.findAll().first { user -> user.email == email }

    }

    fun comparePassword(password: String, passwordEnconde: String): Boolean {
        return BCryptPasswordEncoder().matches(password, passwordEnconde)
    }


}
