package com.bramwork.myretro

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MyRetroConfiguration() {
    var log: Logger = LoggerFactory.getLogger(MyretroApplication::class.java)

    @Bean
    fun commandLineRunner(): CommandLineRunner {
        return CommandLineRunner { args ->
            log.info("[CLR] Args: {}", args.contentToString())
        }
    }

    @Bean
    fun applicationRunner(): ApplicationRunner {
        return ApplicationRunner {
            args: ApplicationArguments ->
            log.info("[AR] Option Args: {}", args.optionNames)
            log.info("[AR] Option Arg Values: {}", args.getOptionValues("option"))
            log.info("[AR] Non Option: {}", args.nonOptionArgs)
        }
    }

    @Bean
    fun applicationReadyEventApplicationListener(): ApplicationListener<ApplicationReadyEvent> {
        return ApplicationListener { event: ApplicationReadyEvent? -> log.info("[AL] Im ready to interact...")}
    }

}