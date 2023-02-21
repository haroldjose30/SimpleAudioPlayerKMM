package dev.haroldjose.simpleaudioplayerkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform