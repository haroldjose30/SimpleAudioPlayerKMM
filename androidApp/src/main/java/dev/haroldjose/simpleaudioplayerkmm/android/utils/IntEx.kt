package dev.haroldjose.simpleaudioplayerkmm.android.utils

import kotlin.math.floor

/**
 * Convert Int number to minutes and seconds
 */
fun Int.timeStampToDuration(): String {
    val totalSeconds = floor(this * 1000 / 1E3).toInt()
    val minutes = totalSeconds / 60
    val remainingSeconds = totalSeconds - (minutes * 60)
    return if (this < 0) "--:--"
    else "%d:%02d".format(minutes, remainingSeconds)
}

//TODO: MOve to shared with expect/Actual