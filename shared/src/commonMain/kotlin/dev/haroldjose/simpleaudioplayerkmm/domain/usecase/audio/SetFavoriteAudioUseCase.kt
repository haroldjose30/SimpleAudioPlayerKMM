package dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio

import dev.haroldjose.simpleaudioplayerkmm.data.repository.local.IAudioLocalRepository
import dev.haroldjose.simpleaudioplayerkmm.domain.mapper.toDTO
import dev.haroldjose.simpleaudioplayerkmm.domain.model.AudioEntry

class SetFavoriteAudioUseCase(
    private val repository: IAudioLocalRepository
)  {

    suspend fun execute(
        audio: AudioEntry
    ): Unit {

        return repository.setFavorite(
            audio.toDTO()
        )
    }
}