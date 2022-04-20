package br.com.projetokotlin.controller

import br.com.projetokotlin.dto.PostForm
import br.com.projetokotlin.dto.PostView
import br.com.projetokotlin.dto.UpdatePostForm
import br.com.projetokotlin.mapper.PostViewMapper
import br.com.projetokotlin.model.Post
import br.com.projetokotlin.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/post")
class PostController(private val postService: PostService,
                     private val postViewMapper: PostViewMapper) {

    @PostMapping
    @Transactional
    fun savePost(@RequestBody @Valid postForm: PostForm): ResponseEntity<PostView> {
        val post = postService.createPost(postForm)
        val postMapper = postViewMapper.map(post)
        return ResponseEntity.status(201).body(postMapper)
    }

    @GetMapping
    fun getAllPost(): List<PostView> {
        return postService.getAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Post? {
        return postService.findById(id)
    }

    @PutMapping("/{id}")
    @Transactional
    fun updatePost(@PathVariable id: Long, @RequestBody @Valid updatePostForm: UpdatePostForm): ResponseEntity<PostView> {
        val updatePost = postService.update(id, updatePostForm)
        return ResponseEntity.status(200).body(updatePost)
    }
}