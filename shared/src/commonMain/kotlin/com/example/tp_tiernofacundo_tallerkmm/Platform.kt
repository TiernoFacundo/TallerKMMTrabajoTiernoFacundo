package com.example.tp_tiernofacundo_tallerkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform