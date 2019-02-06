package com.thinkdevs.security.config

import com.thinkdevs.security.services.CustomUserDetailsService
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.Authentication
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class WebSecurityConfig:WebSecurityConfigurerAdapter() {

    @Autowired
    private val bCryptPasswordEncoder: BCryptPasswordEncoder? = null

    @Autowired
    var customizeAuthenticationSuccessHandler: CustomizeAuthenticationSuccessHandler? = null

    @Bean
    fun mongoUserDetails(): UserDetailsService {
        return CustomUserDetailsService()
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder?) {
        val userDetailsService = mongoUserDetails()
        auth!!
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)

    }
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .and().csrf().disable()

    }

    @Throws(IOException::class, ServletException::class)
    fun onAuthenticationSuccess(request: HttpServletRequest,
                                response: HttpServletResponse, authentication: Authentication) {
        //set our response to OK status
        response.status = HttpServletResponse.SC_OK

        for (auth in authentication.authorities) {
            if ("ADMIN" == auth.authority) {
                response.sendRedirect("/dashboard")
            }
        }
    }
}