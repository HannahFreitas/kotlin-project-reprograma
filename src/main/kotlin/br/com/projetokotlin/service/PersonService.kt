package br.com.projetokotlin.service

import br.com.projetokotlin.dto.PersonForm
import br.com.projetokotlin.dto.PersonView
import br.com.projetokotlin.mapper.PersonFormMapper
import br.com.projetokotlin.mapper.PersonViewMapper
import br.com.projetokotlin.model.Person
import br.com.projetokotlin.model.RolesStatus
import org.springframework.stereotype.Service

@Service
class PersonService(private var persons: MutableList<Person> = mutableListOf(),
                    private val personViewMapper: PersonViewMapper,
                    private val personFormMapper: PersonFormMapper,
                    private val userService: UserService
) {
    fun savePerson(personForm: PersonForm): PersonView {
        val role = userService.findById(personForm.idUser).role
        if(role != RolesStatus.PF) {
            throw Exception("Nao é permitido cadastrar uma ${role.display}, só é permitido ${RolesStatus.PF.display}!")
        }
        val person = personFormMapper.map(personForm)
        person.id = persons.size.toLong() + 1
        persons.add(person)

        return personViewMapper.map(person)
    }

    fun getAll(): List<PersonView> {
        return persons.map { personViewMapper.map(it) }
    }

    fun findById(id: Long): PersonView {
        val personId = persons.first { id == it.id }
        return personViewMapper.map(personId)
    }

}