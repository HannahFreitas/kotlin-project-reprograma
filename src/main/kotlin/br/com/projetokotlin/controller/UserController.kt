package br.com.projetokotlin.controller

import br.com.projetokotlin.dto.LoginForm
import br.com.projetokotlin.dto.UserForm
import br.com.projetokotlin.dto.UserView
import br.com.projetokotlin.message.Message
import br.com.projetokotlin.service.JwtTokenAuthenticationService
import br.com.projetokotlin.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService, private val jwtService: JwtTokenAuthenticationService) {
    @PostMapping
    @Transactional
    fun register(@RequestBody @Valid userForm: UserForm): ResponseEntity<UserView> {
        val userView = userService.save(userForm)
        return ResponseEntity.status(201).body(userView)
    }

    @PostMapping("/login")
    @Transactional
    fun login(@RequestBody @Valid loginForm: LoginForm, response: HttpServletResponse): ResponseEntity<Any> {
        val user = userService.findByEmail(loginForm.email)
        val password = userService.comparePassword(loginForm.password, user.password)

        if(!password) {
            return ResponseEntity.badRequest().body(Message("Invalid Password!"))
        }


        val issuer = user.id.toString()
        jwtService.addAuthentication(response, user.email, issuer)


        return ResponseEntity.ok(Message("Sucess login"))
    }

    @GetMapping
    fun user(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body(Message("Unauthenticated"))
            }

            val body = jwtService.addCookie(jwt)

            return ResponseEntity.ok(userService.findById(body))
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(Message("Unauthenticated"))
        }
    }
}