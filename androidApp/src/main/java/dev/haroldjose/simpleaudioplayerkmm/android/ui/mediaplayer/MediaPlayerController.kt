package dev.haroldjose.simpleaudioplayerkmm.android.ui.mediaplayer

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*

/**
 * Media player controller wrapped as a ViewModel exposing its current state
 */
abstract class MediaPlayerController : ViewModel(), IMediaPlayerController {



    // object of media player
    private val mediaPlayer = MediaPlayer()

    // sealed class for handling different media player states
    private val _mediaPlayerState = MutableStateFlow<MediaPlayerState>(MediaPlayerState.None)
    val mediaPlayerState = _mediaPlayerState.asStateFlow()

    // Media player attributes
    private val attributes = AudioAttributes
        .Builder()
        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
        .build()

    /**
     * Media player click handler
     */
    override fun audioSelected(url: String) {
        when (mediaPlayerState.value) {
            MediaPlayerState.Started -> {
                if (mediaPlayer.isPlaying)
                    pauseMediaPlayer()
                else
                    startMediaPlayer()
            }
            MediaPlayerState.Paused, MediaPlayerState.Initialized, MediaPlayerState.Finished -> startMediaPlayer()
            else -> {
                initializeMediaPlayer(url)
            }
        }
    }


    val timer = Timer()
    val monitor = object : TimerTask() {
        override fun run() {
           mediaPlayer.duration
        }
    }

    /**
     * Initialize media player with given url
     */
    override fun initializeMediaPlayer(url: String) {
        try {
            mediaPlayer.setAudioAttributes(attributes)
            mediaPlayer.setDataSource(url)
            mediaPlayer.prepare()
            _mediaPlayerState.update { MediaPlayerState.Initialized }
            timer.schedule(monitor, 1000, 1000)

            startMediaPlayer()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Start media player
     */
    override fun startMediaPlayer() {
        try {
            mediaPlayer.start()
            _mediaPlayerState.update { MediaPlayerState.Started }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Pause media player
     */
    override fun pauseMediaPlayer() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
        _mediaPlayerState.update { MediaPlayerState.Paused }
    }


    /**
     * Release media player
     */
    override fun releaseMediaPlayer() {
        mediaPlayer.stop()
        mediaPlayer.release()
        _mediaPlayerState.update { MediaPlayerState.None }

    }

    /**
     * Seek to new position
     */
    override fun seekMediaPlayer(newPosition: Int) {
        mediaPlayer.seekTo(newPosition)
    }
}

/**
 * Media player state
 *
 * @constructor Create empty Media player state
 */
sealed class MediaPlayerState {
    object None : MediaPlayerState()
    object Initialized : MediaPlayerState()
    object Started : MediaPlayerState()
    object Paused : MediaPlayerState()
    object Finished : MediaPlayerState()
}