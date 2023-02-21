package dev.haroldjose.simpleaudioplayerkmm.data.repository

import dev.haroldjose.simpleaudioplayerkmm.data.response.AudioEntryDTO

public interface IAudioRepository {
    suspend fun getAll(): List<AudioEntryDTO>

}