package com.appmkg.mkg

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class webConfig : WebMvcConfigurer{
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000","http://localhost:8000","http://localhost:4200")
            .allowedMethods("GET", "PUT", "DELETE","PATCH","POST","OPTIONS")
                .allowCredentials(true)



    }
}
