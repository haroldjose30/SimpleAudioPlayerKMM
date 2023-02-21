package dev.haroldjose.simpleaudioplayerkmm.domain.model

/**
 * Audio object for Domain Layer
 */
data class AudioEntry(
    val uuid: String,
    val title: String,
    val audio: String,
    val cover: String,
    val totalDurationMs: Int,
    var isFavorite: Boolean = false,
    var rating: Int = 0
) {
    val totalDurationSeconds: Int = totalDurationMs.toSeconds()
}

private fun Int.toSeconds(): Int {
    return this/1000
}
