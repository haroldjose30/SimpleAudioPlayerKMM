package dev.haroldjose.simpleaudioplayerkmm.android.ui.mediaplayer

interface IMediaPlayerController {
    /**
     * Media player click handler
     */
    fun audioSelected(url: String)

    /**
     * Initialize media player with given url
     */
    fun initializeMediaPlayer(url: String)

    /**
     * Start media player
     */
    fun startMediaPlayer()

    /**
     * Pause media player
     */
    fun pauseMediaPlayer()

    /**
     * Release media player
     */
    fun releaseMediaPlayer()

    /**
     * Seek to new position
     */
    fun seekMediaPlayer(newPosition: Int)
}