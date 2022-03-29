package br.com.projetokotlin.mapper

import br.com.projetokotlin.dto.UserForm
import br.com.projetokotlin.model.User
import org.springframework.stereotype.Component

@Component
class UserFormMapper: Mapper<UserForm, User> {

    override fun map(t: UserForm): User {
        return User(
            email = t.email,
            password = t.password,
            role = t.role
        )
    }

}
