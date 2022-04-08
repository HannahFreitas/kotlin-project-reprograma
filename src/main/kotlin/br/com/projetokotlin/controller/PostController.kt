package br.com.projetokotlin.controller

import br.com.projetokotlin.dto.PostForm
import br.com.projetokotlin.dto.PostView
import br.com.projetokotlin.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/post")
class PostController(private val postService: PostService) {

    @PostMapping
    fun savePost(@RequestBody @Valid postForm: PostForm): ResponseEntity<PostView> {
        val post = postService.createPost(postForm)
        return ResponseEntity.status(201).body(post)
    }
}