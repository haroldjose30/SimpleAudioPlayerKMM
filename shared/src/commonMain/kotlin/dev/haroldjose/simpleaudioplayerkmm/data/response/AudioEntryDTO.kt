package dev.haroldjose.simpleaudioplayerkmm.data.response

import kotlinx.serialization.Serializable
import dev.haroldjose.simpleaudioplayerkmm.data.generateUUID

/**
 * Audio object for Data Layer
 */
@Serializable
data class AudioEntryDTO(
    val uuid: String = generateUUID(),
    val title: String,
    val audio: String,
    val cover: String,
    val totalDurationMs: Int,
    var isFavorite: Boolean = false,
    var rating: Int = 0
)