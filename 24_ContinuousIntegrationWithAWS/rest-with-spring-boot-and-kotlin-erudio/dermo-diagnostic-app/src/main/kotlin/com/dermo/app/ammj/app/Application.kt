package com.dermo.app.ammj.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan(
    "com.dermo.app.ammj"
)
@EntityScan("com.dermo.app.ammj.domain.entity")
@EnableJpaRepositories("com.dermo.app.ammj.domain.repository")
@EnableCaching
class Application

fun main(args: Array<String>) {
    val app = SpringApplication(Application::class.java)
    app.run(*args)
}
