package dev.haroldjose.simpleaudioplayerkmm.data.repository.local

import dev.haroldjose.simpleaudioplayerkmm.data.repository.remote.IAudioRemoteRepository
import dev.haroldjose.simpleaudioplayerkmm.data.response.AudioEntryDTO
import kotlin.native.concurrent.ThreadLocal


internal class AudioInMemoryLocalRepository(
    private val remoteRepository: IAudioRemoteRepository
): IAudioLocalRepository {

    @ThreadLocal
    companion object {
        //TODO: save data on local database
        private var datasource = mutableListOf<AudioEntryDTO>()
    }

    override suspend fun getAll(): List<AudioEntryDTO> {

        if (datasource.isEmpty()) {

            datasource = remoteRepository.getAll().toMutableList()
        }
        return datasource
    }

    override suspend fun getById(
        uuid: String
    ): AudioEntryDTO? {

        return datasource.firstOrNull { it.uuid == uuid }
    }

    override suspend fun updateAudio(
        audio: AudioEntryDTO
    ) {

        val index = datasource.indexOfFirst { it.uuid == audio.uuid }
        if (index >= 0) {
            datasource[index] = audio
        }
    }
}