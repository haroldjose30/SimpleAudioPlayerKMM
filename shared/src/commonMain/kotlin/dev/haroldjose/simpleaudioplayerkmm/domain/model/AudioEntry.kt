package dev.haroldjose.simpleaudioplayerkmm.domain.model

import kotlinx.serialization.Serializable

/**
 * Audio object for Domain Layer
 */
@Serializable
data class AudioEntry(
    val title: String,
    val audio: String,
    val cover: String,
    val totalDurationMs: Int,
    var isFavorite: Boolean = false
)

