package com.bramwork.myretro

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "service")
class MyRetroProperties(var users: Users) {}

class Users {
    var server: String? = null
    var port: Int? = null
    var username: String? = null
    var password: String? = null
}