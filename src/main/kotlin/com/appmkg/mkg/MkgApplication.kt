package com.appmkg.mkg

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class MkgApplication

fun main(args: Array<String>) {
	runApplication<MkgApplication>(*args)
}

