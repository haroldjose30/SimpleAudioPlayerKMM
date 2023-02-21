package dev.haroldjose.simpleaudioplayerkmm.data.repository

import dev.haroldjose.simpleaudioplayerkmm.data.response.AudioEntryDTO
import kotlinx.coroutines.delay

public class AudioInMemoryRepository: IAudioRepository {

    companion object {
        private val datasource = mutableListOf<AudioEntryDTO>(
            AudioEntryDTO(
                title = "Oceansound",
                audio = "https://nomad5.com/data/skoove/Oceansound.mp3",
                cover = "https://nomad5.com/data/skoove/Oceansound.png",
                totalDurationMs = 14448
            ),
            AudioEntryDTO(
                title = "Nightlife",
                audio = "https://nomad5.com/data/skoove/Nightlife.mp3",
                cover = "https://nomad5.com/data/skoove/Nightlife.png",
                totalDurationMs = 15696
            ),
            AudioEntryDTO(
                title = "Waking Me",
                audio = "https://nomad5.com/data/skoove/Waking_Me.mp3",
                cover = "https://nomad5.com/data/skoove/Waking_Me.png",
                totalDurationMs = 13776
            )
        )
    }

    override suspend fun getAll(): List<AudioEntryDTO> {
        delay(2000L) //
        return datasource.toList()
    }

}