package com.bramwork.myretro.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@EnableConfigurationProperties(MyRetroProperties::class)
@Configuration
class MyRetroConfiguration