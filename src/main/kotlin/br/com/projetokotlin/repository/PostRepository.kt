package br.com.projetokotlin.repository

import br.com.projetokotlin.model.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long> {
    //add query status
}