package br.com.projetokotlin.controller

import br.com.projetokotlin.dto.LoginForm
import br.com.projetokotlin.dto.UserForm
import br.com.projetokotlin.dto.UserView
import br.com.projetokotlin.message.Message
import br.com.projetokotlin.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {
    @PostMapping("/register")
    fun register(@RequestBody @Valid userForm: UserForm, uriBuilder: UriComponentsBuilder): ResponseEntity<UserView> {
        val userView = userService.save(userForm)
        val uri = uriBuilder.path("/user/${userView.id}").build().toUri()
        return ResponseEntity.created(uri).body(userView)
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid loginForm: LoginForm, response: HttpServletResponse): ResponseEntity<Any> {
        val user = userService.findByEmail(loginForm.email)
        val password = userService.comparePassword(loginForm.password, user.password)

        if(!password) {
            return ResponseEntity.badRequest().body(Message("Invalid Password!"))
        }


        val issuer = user.id.toString()
        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) //1 dia
            .signWith(SignatureAlgorithm.HS512, "hannahdariellyrafaelaingrydttalitareprogramernacreditassquadrelationsdatriboautofintentandofazeroprojetofuncionar").compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("Sucess login"))
    }

    @GetMapping
    fun user(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body(Message("Unauthenticated"))
            }

            val body = Jwts.parser()
                .setSigningKey("hannahdariellyrafaelaingrydttalitareprogramernacreditassquadrelationsdatriboautofintentandofazeroprojetofuncionar")
                .parseClaimsJws(jwt).body

            return ResponseEntity.ok(userService.getById(body.issuer.toLong()))
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(Message("Unauthenticated"))
        }
    }
}