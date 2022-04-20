package br.com.projetokotlin.controller

import br.com.projetokotlin.dto.PersonForm
import br.com.projetokotlin.dto.PersonView
import br.com.projetokotlin.model.Person
import br.com.projetokotlin.service.PersonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/person")
class PersonController(private val personService: PersonService) {
    @PostMapping
    @Transactional
    fun register(@RequestBody @Valid personForm: PersonForm): ResponseEntity<PersonView> {
            val person = personService.savePerson(personForm)
            return ResponseEntity.status(201).body(person)
    }

    @GetMapping
    fun person(): List<PersonView> {
        return personService.getAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Person? {
        return personService.findById(id)
    }
}