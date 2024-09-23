package com.gnr.proyectovelocidad1

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform