package br.com.projetokotlin.service

import br.com.projetokotlin.dto.UserForm
import br.com.projetokotlin.dto.UserView
import br.com.projetokotlin.mapper.UserFormMapper
import br.com.projetokotlin.mapper.UserViewMapper
import br.com.projetokotlin.model.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService(private var user: List<User> = ArrayList(),
                  private val userFormMapper: UserFormMapper,
                  private val userViewMapper: UserViewMapper
) {
    fun save(userForm: UserForm): UserView {
        val passwordEnconder = BCryptPasswordEncoder()
        val users = userFormMapper.map(userForm)
        users.id = user.size.toLong() + 1
        users.password = passwordEnconder.encode(userForm.password)
        user = user.plus(users)
        return userViewMapper.map(users)
    }


    fun findByEmail(email: String): User {
        return user.first { email == it.email }
    }

    fun comparePassword(password: String, passwordEnconde: String): Boolean {
        return BCryptPasswordEncoder().matches(password, passwordEnconde)
    }

    fun getById(id: Long): User {
        return user.first { id == it.id }
    }

}
