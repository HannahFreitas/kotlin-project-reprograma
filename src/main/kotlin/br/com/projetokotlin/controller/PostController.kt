package br.com.projetokotlin.controller

import br.com.projetokotlin.dto.PostForm
import br.com.projetokotlin.dto.PostView
import br.com.projetokotlin.dto.UpdatePostForm
import br.com.projetokotlin.mapper.PostViewMapper
import br.com.projetokotlin.model.Post
import br.com.projetokotlin.service.PostService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/post")
class PostController(private val postService: PostService,
                     private val postViewMapper: PostViewMapper) {

    @PostMapping
    @Transactional
    @CacheEvict(value = ["posts"], allEntries = true)
    fun savePost(@RequestBody @Valid postForm: PostForm): ResponseEntity<PostView> {
        val post = postService.createPost(postForm)
        val postMapper = postViewMapper.map(post)
        return ResponseEntity.status(201).body(postMapper)
    }

    @GetMapping
    @Cacheable("posts")
    fun getAllPost(@PageableDefault(size = 5, sort = ["createdAt"], direction = Sort.Direction.DESC) pageable: Pageable): Page<PostView> {
        return postService.getAll(pageable)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Post? {
        return postService.findById(id)
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = ["posts"], allEntries = true)
    fun updatePost(@PathVariable id: Long, @RequestBody @Valid updatePostForm: UpdatePostForm): ResponseEntity<PostView> {
        val updatePost = postService.update(id, updatePostForm)
        return ResponseEntity.status(200).body(updatePost)
    }
}