package br.com.projetokotlin.service

import br.com.projetokotlin.dto.UserForm
import br.com.projetokotlin.dto.UserView
import br.com.projetokotlin.mapper.UserFormMapper
import br.com.projetokotlin.mapper.UserViewMapper
import br.com.projetokotlin.model.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService(private var users: MutableList<User> = mutableListOf(),
                  private val userFormMapper: UserFormMapper,
                  private val userViewMapper: UserViewMapper
) {
    fun save(userForm: UserForm): UserView {
        val passwordEnconder = BCryptPasswordEncoder()
        val user = userFormMapper.map(userForm)
        user.id = users.size.toLong() + 1
        user.password = passwordEnconder.encode(userForm.password)
        users.add(user)
        return userViewMapper.map(user)
    }

    fun findById(id: Long): UserView {
        val userId = users.first { id == it.id }
        return userViewMapper.map(userId)
    }


    fun findByEmail(email: String): User {
        return users.first { email == it.email }
    }

    fun comparePassword(password: String, passwordEnconde: String): Boolean {
        return BCryptPasswordEncoder().matches(password, passwordEnconde)
    }

    fun getById(id: Long): User {
        return users.first { id == it.id }
    }

}
