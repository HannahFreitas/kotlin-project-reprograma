package br.com.projetokotlin.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var email: String = "",
    var password: String = "",
    @Enumerated(EnumType.STRING)
    @Column(name = "userrole")
    val role: RolesStatus,
    @Column(name = "createdat")
    val createdAt: LocalDateTime = LocalDateTime.now()
)