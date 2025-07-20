package com.bramwork.myretro

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.context.annotation.Configuration

@Configuration
class MyRetroConfiguration(arguments: ApplicationArguments) {
    var log: Logger = LoggerFactory.getLogger(MyretroApplication::class.java)

    init {
        log.info("Option Args: {}", arguments.optionNames)
        log.info("Option Arg Values: {}", arguments.getOptionValues("option"))
        log.info("Non Option: {}", arguments.nonOptionArgs)
    }
}