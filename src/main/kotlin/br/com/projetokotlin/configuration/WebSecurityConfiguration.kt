package br.com.projetokotlin.configuration


import br.com.projetokotlin.security.filter.UserBeforeAuthenticationFilter
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http?.authorizeRequests()
            ?.and()
            ?.addFilterBefore(getBeforeAuthenticationFilter(), UserBeforeAuthenticationFilter::class.java)
            ?.formLogin()
            ?.loginPage("/user/login")
            ?.usernameParameter("email")
            ?.permitAll()

    }

    fun getBeforeAuthenticationFilter() : UsernamePasswordAuthenticationFilter {
        val filter = UserBeforeAuthenticationFilter()
        filter.setAuthenticationManager(authenticationManager())
        return filter
    }
}