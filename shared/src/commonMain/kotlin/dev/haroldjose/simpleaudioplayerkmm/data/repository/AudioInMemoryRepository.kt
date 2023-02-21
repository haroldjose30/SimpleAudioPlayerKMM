package dev.haroldjose.simpleaudioplayerkmm.data.repository

import dev.haroldjose.simpleaudioplayerkmm.data.response.AudioEntry

internal class AudioInMemoryRepository: IAudioRepository {

    companion object {
        private val datasource = mutableListOf<AudioEntry>(
            AudioEntry(
                title = "Oceansound",
                audio = "https://nomad5.com/data/skoove/Oceansound.mp3",
                cover = "https://nomad5.com/data/skoove/Oceansound.png",
                totalDurationMs = 14448
            ),
            AudioEntry(
                title = "Nightlife",
                audio = "https://nomad5.com/data/skoove/Nightlife.mp3",
                cover = "https://nomad5.com/data/skoove/Nightlife.png",
                totalDurationMs = 15696
            ),
            AudioEntry(
                title = "Waking Me",
                audio = "https://nomad5.com/data/skoove/Waking_Me.mp3",
                cover = "https://nomad5.com/data/skoove/Waking_Me.png",
                totalDurationMs = 13776
            )
        )
    }

    override suspend fun readAll(): List<AudioEntry> {

        return datasource.toList()
    }

}