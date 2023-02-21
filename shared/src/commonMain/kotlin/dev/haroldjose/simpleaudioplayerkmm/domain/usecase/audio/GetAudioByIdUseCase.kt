package dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio


import dev.haroldjose.simpleaudioplayerkmm.data.repository.local.IAudioLocalRepository
import dev.haroldjose.simpleaudioplayerkmm.domain.mapper.toModel
import dev.haroldjose.simpleaudioplayerkmm.domain.model.AudioEntry

class GetAudioByIdUseCase(
    private val repository: IAudioLocalRepository
)  {

    suspend fun execute(uuid: String): AudioEntry? {

        return repository.getById(uuid)?.toModel()
    }
}