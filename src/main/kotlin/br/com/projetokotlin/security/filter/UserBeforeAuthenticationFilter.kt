package br.com.projetokotlin.security.filter

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class UserBeforeAuthenticationFilter : UsernamePasswordAuthenticationFilter() {
    fun UserBeforeAuthenticationFilter() {
        usernameParameter = "email"
        super.setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher("/user/login", "POST"))
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val email = request?.getParameter("email")
        println("The user $email is about to login")
        return super.attemptAuthentication(request, response)
    }
}