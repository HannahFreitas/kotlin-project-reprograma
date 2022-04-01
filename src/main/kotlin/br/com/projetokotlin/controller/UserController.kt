package br.com.projetokotlin.controller

import br.com.projetokotlin.dto.UserForm
import br.com.projetokotlin.dto.UserView
import br.com.projetokotlin.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {
    @PostMapping
    fun register(@RequestBody @Valid userForm: UserForm, uriBuilder: UriComponentsBuilder): ResponseEntity<UserView> {
        val userView = userService.save(userForm)
        val uri = uriBuilder.path("/user/${userView.id}").build().toUri()
        return ResponseEntity.created(uri).body(userView)
    }
}