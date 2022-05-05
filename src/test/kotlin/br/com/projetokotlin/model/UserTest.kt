package br.com.projetokotlin.model

object UserTest {
    fun build() = User(
        id = 1,
        email = "paulo@gmail.com",
        password = "itsonlyatest",
        role = RolesStatus.PF
    )
}
