package dev.haroldjose.simpleaudioplayerkmm.data.response

import kotlinx.serialization.Serializable

/**
 * Audio object for Data Layer
 */
@Serializable
data class AudioEntryDTO(
    val title: String,
    val audio: String,
    val cover: String,
    val totalDurationMs: Int
)