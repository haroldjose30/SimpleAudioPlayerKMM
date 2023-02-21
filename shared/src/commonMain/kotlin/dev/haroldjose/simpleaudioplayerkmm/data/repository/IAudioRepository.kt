package dev.haroldjose.simpleaudioplayerkmm.data.repository

import dev.haroldjose.simpleaudioplayerkmm.data.response.AudioEntry

internal interface IAudioRepository {

    suspend fun readAll(): List<AudioEntry>

}