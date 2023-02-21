package dev.haroldjose.simpleaudioplayerkmm.domain.mapper

import dev.haroldjose.simpleaudioplayerkmm.data.response.AudioEntryDTO
import dev.haroldjose.simpleaudioplayerkmm.domain.model.AudioEntry


internal fun AudioEntryDTO.toModel(): AudioEntry {
    return AudioEntry(
        uuid = this.uuid,
        title = this.title,
        audio = this.audio,
        cover = this.cover,
        totalDurationMs = this.totalDurationMs,
        isFavorite = this.isFavorite,
        rating = this.rating
    )
}