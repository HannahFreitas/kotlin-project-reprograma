package br.com.projetokotlin.repository

import br.com.projetokotlin.model.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository: JpaRepository<Person, Long> {
}