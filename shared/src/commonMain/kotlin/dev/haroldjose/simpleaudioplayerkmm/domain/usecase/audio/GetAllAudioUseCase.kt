package dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio

import dev.haroldjose.simpleaudioplayerkmm.data.repository.IAudioRepository
import dev.haroldjose.simpleaudioplayerkmm.domain.mapper.toModel
import dev.haroldjose.simpleaudioplayerkmm.domain.model.AudioEntry

class GetAllAudioUseCase(
    private val repository: IAudioRepository
)  {

    suspend fun execute(): List<AudioEntry> {

        return repository.getAll().map {
            it.toModel()
        }
    }
}