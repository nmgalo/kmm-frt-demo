package com.flatrocktech.demo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform