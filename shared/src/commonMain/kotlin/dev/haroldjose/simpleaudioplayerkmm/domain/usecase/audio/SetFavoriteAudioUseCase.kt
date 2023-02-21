package dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio

import dev.haroldjose.simpleaudioplayerkmm.data.repository.local.AudioInMemoryLocalRepository
import dev.haroldjose.simpleaudioplayerkmm.data.repository.local.IAudioLocalRepository
import dev.haroldjose.simpleaudioplayerkmm.domain.mapper.toDTO
import dev.haroldjose.simpleaudioplayerkmm.domain.mapper.toModel
import dev.haroldjose.simpleaudioplayerkmm.domain.model.AudioEntry

class SetFavoriteAudioUseCase(
    private val repository: IAudioLocalRepository
)  {

    suspend fun execute(
        audio: AudioEntry
    ): List<AudioEntry> {

        val audioList = repository.getAll()

        //TODO: this can be improved
        audioList.forEach {
            if (it.uuid == audio.uuid) {
                it.isFavorite = audio.isFavorite
                repository.updateAudio(it)
            } else {
                if (it.isFavorite != false) {
                    it.isFavorite = false
                    repository.updateAudio(it)
                }
            }
        }

        return audioList.map {
            it.toModel()
        }
    }
}