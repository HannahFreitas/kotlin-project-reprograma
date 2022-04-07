package br.com.projetokotlin.mapper

import br.com.projetokotlin.dto.PersonView
import br.com.projetokotlin.model.Person
import org.springframework.stereotype.Component

@Component
class PersonViewMapper: Mapper<Person, PersonView> {
    override fun map(t: Person): PersonView {
        return PersonView(
            id = t.id,
            name = t.name,
            createdAt = t.createdAt,
            updateAt = t.updateAt
        )
    }

}