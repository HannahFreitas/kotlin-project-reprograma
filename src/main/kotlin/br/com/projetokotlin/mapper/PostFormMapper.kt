package br.com.projetokotlin.mapper

import br.com.projetokotlin.dto.PostForm
import br.com.projetokotlin.model.Post
import br.com.projetokotlin.service.PersonService
import org.springframework.stereotype.Component

@Component
class PostFormMapper(private val personService: PersonService): Mapper<PostForm, Post>{
    override fun map(t: PostForm) = Post(
        title = t.title!!,
        message = t.message!!,
        person = personService.findById(t.person_id!!)
    )

}
