package br.com.projetokotlin.mapper

import br.com.projetokotlin.dto.PersonForm
import br.com.projetokotlin.model.Person
import br.com.projetokotlin.service.UserService
import org.springframework.stereotype.Component

@Component
class PersonFormMapper(private val serviceUser: UserService): Mapper<PersonForm, Person> {
    override fun map(t: PersonForm) = Person(
            name = t.name,
            address = t.adress,
            phone = t.phone,
            whatsapp = t.whatsapp,
            cpf = t.cpf,
            user = serviceUser.findById(t.idUser)
        )
}