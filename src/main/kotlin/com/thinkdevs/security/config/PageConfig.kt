package com.thinkdevs.security.config

import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder



class PageConfig:WebMvcConfigurer {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}