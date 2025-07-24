package com.bramwork.myretro.exception

import java.lang.RuntimeException

class RetroBoardNotFoundException : RuntimeException {
    constructor() : super("RetroBoard Not Found")
    constructor(message: String?) : super(String.format("RetroBoard Not Found: {}", message))
    constructor(message: String?, cause: Throwable?) : super(String.format("RetroBoard Not Found: {}", message), cause)
}