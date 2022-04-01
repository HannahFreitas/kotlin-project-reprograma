package br.com.projetokotlin.service

import br.com.projetokotlin.dto.UserForm
import br.com.projetokotlin.dto.UserView
import br.com.projetokotlin.mapper.UserFormMapper
import br.com.projetokotlin.mapper.UserViewMapper
import br.com.projetokotlin.model.User
import org.springframework.stereotype.Service


@Service
class UserService(private var user: List<User> = ArrayList(),
                  private val userFormMapper: UserFormMapper,
                  private val userViewMapper: UserViewMapper
) {
    fun save(userForm: UserForm): UserView {
        val users = userFormMapper.map(userForm)
        users.id = user.size.toLong() + 1
        user = user.plus(users)
        return userViewMapper.map(users)
    }

    fun findByEmail(email: String): User {
        return user.first { email == it.email }
    }

}
