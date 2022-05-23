package br.com.projetokotlin.configuration


import br.com.projetokotlin.security.filter.UserBeforeAuthenticationFilter
import br.com.projetokotlin.service.ImplementUserDetailsService
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration(private val implementUserDetailsService: ImplementUserDetailsService) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST,"/user").permitAll()
            .antMatchers(HttpMethod.POST,"/user/login").permitAll()
            .antMatchers(HttpMethod.GET,"/user").permitAll()
            .antMatchers(HttpMethod.PUT,"/user").permitAll()
            .antMatchers(HttpMethod.DELETE,"/user").permitAll()
            .antMatchers(HttpMethod.GET,"/person").permitAll()
            .antMatchers(HttpMethod.POST,"/person").permitAll()
            .antMatchers(HttpMethod.PUT,"/person").permitAll()
            .antMatchers(HttpMethod.DELETE,"/person").permitAll()
            .antMatchers(HttpMethod.GET,"/post").permitAll()
            .antMatchers(HttpMethod.POST,"/post").permitAll()
            .antMatchers(HttpMethod.PUT,"/post").permitAll()
            .antMatchers(HttpMethod.DELETE,"/post").permitAll()
            .anyRequest()
            .authenticated()

    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(implementUserDetailsService).passwordEncoder(BCryptPasswordEncoder())
    }

    fun getBeforeAuthenticationFilter() : UsernamePasswordAuthenticationFilter {
        val filter = UserBeforeAuthenticationFilter()
        filter.setAuthenticationManager(authenticationManager())
        return filter
    }


}