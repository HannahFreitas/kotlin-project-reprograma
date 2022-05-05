package br.com.projetokotlin.repository


import br.com.projetokotlin.model.Post
import br.com.projetokotlin.model.StatusPost
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PostRepository: JpaRepository<Post, Long> {

    @Query(value = "SELECT t FROM Post t WHERE t.status = :status")
        fun findByStatus(status: StatusPost, pageable: Pageable): Page<Post>
}