package dev.haroldjose.simpleaudioplayerkmm.data.repository.local

import dev.haroldjose.simpleaudioplayerkmm.data.response.AudioEntryDTO

public interface IAudioLocalRepository {
    suspend fun getAll(): List<AudioEntryDTO>
    suspend fun getById(uuid: String): AudioEntryDTO?
    suspend fun updateAudio(audioEntryDTO: AudioEntryDTO)

}