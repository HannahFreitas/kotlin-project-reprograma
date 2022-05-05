package br.com.projetokotlin.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "post")
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    var message: String,
    @ManyToOne
    @JoinColumn(name = "person_id")
    val person: Person?,//trocar pra person
    @Enumerated(value = EnumType.STRING)
    val status: StatusPost = StatusPost.ABERTO,
    @Column(name = "createdat")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updatedat")
    val updatedAt: LocalDateTime? = null
)

