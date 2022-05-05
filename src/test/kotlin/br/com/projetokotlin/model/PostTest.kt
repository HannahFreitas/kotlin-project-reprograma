package br.com.projetokotlin.model

object PostTest {
    fun build() = Post(
        id = 1,
        title = "Precisando de ajuda",
        message = "Ajuda...",
        person = PersonTest.build(),
        status = StatusPost.ABERTO
    )
}