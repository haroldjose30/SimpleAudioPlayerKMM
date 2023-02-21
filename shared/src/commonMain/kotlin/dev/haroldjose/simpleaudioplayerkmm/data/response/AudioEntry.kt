package dev.haroldjose.simpleaudioplayerkmm.data.response

import kotlinx.serialization.Serializable

/**
 * Audio object
 */
@Serializable
internal data class AudioEntry(
    val title: String,
    val audio: String,
    val cover: String,
    val totalDurationMs: Int
)