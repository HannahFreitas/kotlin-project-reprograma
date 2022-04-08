package br.com.projetokotlin.service

import br.com.projetokotlin.dto.PostForm
import br.com.projetokotlin.dto.PostView
import br.com.projetokotlin.mapper.PostFormMapper
import br.com.projetokotlin.mapper.PostViewMapper
import br.com.projetokotlin.model.Post
import org.springframework.stereotype.Service

@Service
class PostService(private val posts: MutableList<Post> = mutableListOf(),
                  private val postFormMapper: PostFormMapper,
                  private val postViewMapper: PostViewMapper,
                  private val personService: PersonService
) {
    fun createPost(postForm: PostForm): PostView {
        val person = personService.findById(postForm.idPerson).id
        if(person != postForm.idPerson) {
            throw Exception("Nao é possível criar essa publicaçao, pois o usuário nao existe!")
        }
        val post = postFormMapper.map(postForm)
        post.id = posts.size.toLong() + 1
        posts.add(post)
        return postViewMapper.map(post)
    }

    fun getAll(): List<PostView> {
        return posts.map { postViewMapper.map(it) }
    }

    fun findById(id: Long): PostView {
        val postId = posts.first { id == it.id}
        return postViewMapper.map(postId)
    }

}
