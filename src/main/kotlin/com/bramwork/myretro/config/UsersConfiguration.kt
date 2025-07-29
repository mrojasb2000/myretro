package com.bramwork.myretro.config

data class UsersConfiguration(
    var server: String? = null,
    var port: Int? = null,
    var username: String? = null,
    var password: String? = null
)