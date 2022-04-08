package br.com.projetokotlin.mapper

import br.com.projetokotlin.dto.PostView
import br.com.projetokotlin.model.Post
import org.springframework.stereotype.Component

@Component
class PostViewMapper: Mapper<Post, PostView> {
    override fun map(t: Post) = PostView(
        id = t.id,
        title = t.title,
        message = t.message,
        person = t.person,
        status = t.status,
        createdAt = t.createdAt,
        uptadedAt = t.updatedAt
    )
}