package br.com.projetokotlin.mapper

import br.com.projetokotlin.dto.UserView
import br.com.projetokotlin.model.User
import org.springframework.stereotype.Component

@Component
class UserViewMapper: Mapper<User, UserView> {
    override fun map(t: User): UserView {
        return UserView(
            id = t.id,
            email = t.email,
            role = t.role,
            createdAt = t.createdAt
        )
    }

}
