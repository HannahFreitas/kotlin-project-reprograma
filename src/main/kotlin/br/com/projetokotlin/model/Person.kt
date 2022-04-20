package br.com.projetokotlin.model

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "person")
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val name: String,
    val address: String,
    val phone: String,
    val whatsapp: Boolean,
    val cpf: String,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User?,//trocar pra user
    @Column(name = "createdat")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updatedat")
    val updateAt: LocalDateTime? = null
)