package br.com.projetokotlin.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    var message: String,
    @ManyToOne
    val person: Person?,//trocar pra person
    @Enumerated(value = EnumType.STRING)
    val status: StatusPost = StatusPost.ABERTO,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)