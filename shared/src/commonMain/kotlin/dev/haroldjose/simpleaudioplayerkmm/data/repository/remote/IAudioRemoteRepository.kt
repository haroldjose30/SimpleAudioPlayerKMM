package dev.haroldjose.simpleaudioplayerkmm.data.repository.remote

import dev.haroldjose.simpleaudioplayerkmm.data.response.AudioEntryDTO

internal interface IAudioRemoteRepository {
    suspend fun getAll(): List<AudioEntryDTO>
}