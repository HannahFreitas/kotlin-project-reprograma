package br.com.projetokotlin.service

import br.com.projetokotlin.dto.PostForm
import br.com.projetokotlin.dto.PostView
import br.com.projetokotlin.dto.UpdatePostForm
import br.com.projetokotlin.exception.NotFoundException
import br.com.projetokotlin.mapper.PostFormMapper
import br.com.projetokotlin.mapper.PostViewMapper
import br.com.projetokotlin.model.Post
import br.com.projetokotlin.repository.PostRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostService(private val postRepository: PostRepository,
                  private val notFoundMessage: String = "Postagem nao encontrado.",
                  private val postFormMapper: PostFormMapper,
                  private val postViewMapper: PostViewMapper,
                  private val personService: PersonService
) {
    fun createPost(postForm: PostForm): Post {
        personService.findById(postForm.idPerson)?.id

        val post = postFormMapper.map(postForm)
        postRepository.save(post)

        return post
    }

    fun getAll(pageable: Pageable): Page<PostView> {
        return postRepository.findAll(pageable).map { postViewMapper.map(it) }
    }

    fun findById(id: Long): Post? {
        return postRepository.findById(id).orElseThrow{ NotFoundException(notFoundMessage) }
    }

    fun update(id: Long, updatePostForm: UpdatePostForm): PostView {
        val post = postRepository.findAll().first { it.id == id } ?: throw NotFoundException(notFoundMessage)
        post.title = updatePostForm.title
        post.message = updatePostForm.message

        return postViewMapper.map(post)
    }

}
