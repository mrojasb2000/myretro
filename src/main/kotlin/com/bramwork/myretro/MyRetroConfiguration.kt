package com.bramwork.myretro

import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableConfigurationProperties(MyRetroProperties::class)
@Configuration
class MyRetroConfiguration {
    var log = LoggerFactory.getLogger(MyretroApplication::class.java)

    @Bean
    fun init(myRetroProperties: MyRetroProperties): ApplicationListener<ApplicationReadyEvent> {
        return ApplicationListener { event: ApplicationReadyEvent? ->
            log.info(
                "\nThe users service properties are:\n- Server: {}\n- Port: {}\n- Username: {}\n- Password: {}"
                ,myRetroProperties.users!!.server
                ,myRetroProperties.users.port
                ,myRetroProperties.users.username
                ,myRetroProperties.users.password
            )
        }
    }
}