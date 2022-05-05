package br.com.projetokotlin.model

object PersonTest {
    fun build() = Person(
        id = 1,
        name = "Paulo",
        address = "Logo ali",
        phone = "111111111111",
        whatsapp = true,
        cpf = "11111111111",
        user = UserTest.build()
    )

}
