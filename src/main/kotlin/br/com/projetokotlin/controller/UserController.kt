package br.com.projetokotlin.controller

import br.com.projetokotlin.dto.UserForm
import br.com.projetokotlin.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("user")
class UserController(private val userService: UserService) {
    @PostMapping("register")
    fun register(@RequestBody @Valid userForm: UserForm) {
        return userService.cadastrar(userForm)
    }
}