package br.com.projetokotlin.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@Service
@Component
class JwtTokenAuthenticationService {

    val EXPIRATION_TIME = 60 * 24 * 1000
    val SECRET = "hannahdariellyrafaelaingrydttalitareprogramernacreditassquadrelationsdatriboautofintentandofazeroprojetofuncionar"
    val TOKEN_PREFIX = "Bearer"
    val HEADER_STRING = "Authorization"

    fun addAuthentication(response: HttpServletResponse, email: String, issuer: String) {
        val JWT = Jwts.builder()
            .setIssuer(issuer)
            .setSubject(email)
            .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME)) //1 dia
            .signWith(SignatureAlgorithm.HS512, SECRET.toByteArray())
            .compact()

        val TOKEN = TOKEN_PREFIX + " " + JWT

        response.addHeader(HEADER_STRING, TOKEN)

        val cookie = Cookie("jwt", JWT)
        cookie.isHttpOnly = true

        response.addCookie(cookie)

    }

    fun addCookie(jwt: String): Long {
        val body = Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(jwt)

        return body.body.issuer.toLong()
    }
}