package com.bramwork.myretro

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class MyRetroConfiguration() {
    var log = LoggerFactory.getLogger(MyretroApplication::class.java)

    @Value("\${users.server}")
    var server: String? = null

    @Value("\${users.port}")
    var port: Int? = null

    @Value("\${users.username}")
    var username: String? = null

    @Value("\${users.password}")
    var password: String? = null

    @Bean
    fun init(): ApplicationListener<ApplicationReadyEvent> {
        return ApplicationListener { event: ApplicationReadyEvent? ->
            log.info(
                "\nThe users service properties are:\n- Server: {}\n- Port: {}\n- Username: {}\n- Password: {}"
                ,server
                ,port
                ,username
                ,password
            )
        }
    }

}