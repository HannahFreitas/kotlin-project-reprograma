package br.com.projetokotlin.service

import br.com.projetokotlin.dto.PersonForm
import br.com.projetokotlin.dto.PersonView
import br.com.projetokotlin.exception.NotFoundException
import br.com.projetokotlin.mapper.PersonFormMapper
import br.com.projetokotlin.mapper.PersonViewMapper
import br.com.projetokotlin.model.Person
import br.com.projetokotlin.model.RolesStatus
import br.com.projetokotlin.repository.PersonRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonService(private val personRepository: PersonRepository,
                    private val notFoundMessage: String = "Cadastro nao encontrado.",
                    private val personViewMapper: PersonViewMapper,
                    private val personFormMapper: PersonFormMapper,
                    private val userService: UserService
) {
    fun savePerson(personForm: PersonForm): PersonView {
        val role = userService.findById(personForm.user_id)?.role
        if(role != RolesStatus.PF) {
            if (role != null) {
                throw Exception("Nao é permitido cadastrar uma ${role.display}, só é permitido ${RolesStatus.PF.display}!")
            }
        }

        val person = personFormMapper.map(personForm)
        personRepository.save(person)

        return personViewMapper.map(person)
    }

    fun getAll(): List<PersonView> {
        return personRepository.findAll().map { personViewMapper.map(it) }
    }

    fun findById(id: Long?): Person? {
        return personRepository.findById(id!!).orElseThrow{NotFoundException(notFoundMessage)}
    }

}