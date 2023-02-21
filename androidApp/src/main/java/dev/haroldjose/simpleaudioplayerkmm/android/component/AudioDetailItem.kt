package dev.haroldjose.simpleaudioplayerkmm.android.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import dev.haroldjose.simpleaudioplayerkmm.android.R
import dev.haroldjose.simpleaudioplayerkmm.android.ui.mediaplayer.IMediaPlayerController
import dev.haroldjose.simpleaudioplayerkmm.android.ui.mediaplayer.MediaPlayerController
import dev.haroldjose.simpleaudioplayerkmm.android.utils.timeStampToDuration
import dev.haroldjose.simpleaudioplayerkmm.domain.model.AudioEntry

/**
 * Audio item for the detail view
 */
@Composable
fun AudioDetailItem(
    audio: AudioEntry,
    mediaPlayer: MediaPlayerController,
    isAudioPlaying: Boolean,
    isFavorite: Boolean,
    playingTime: Float,
    duration: Int,
    rating: Int,
    onStarClicked: (rating: Int) -> Unit,
    onSliderValueChanged: (value: Float) -> Unit,
    onFavoriteClicked: (favorite: Boolean) -> Unit
) {

    val playingTimeState = remember {
        mutableStateOf(playingTime)
    }

    Column(modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .clickable(interactionSource = remember { MutableInteractionSource() },
                indication = null) {
                mediaPlayer.audioSelected(audio.audio)
            },
            contentAlignment = Alignment.Center
        ) {

            // Audio Cover
            CoilImage(imageModel = audio.cover,
                contentDescription = null,
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = MaterialTheme.colors.surface
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth())

            // Media Player Controller Icons
            Image(painter = painterResource(id = if (isAudioPlaying) R.drawable.ic_pause else R.drawable.ic_play),
                contentDescription = stringResource(id = R.string.contentDescription_audio_is_favorite),
                modifier = Modifier.size(120.dp))

            // audio favorite status element
            FavoriteElement(modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
                favoriteState = isFavorite,
                onClick = {
                    onFavoriteClicked(!isFavorite)
                })
        }

        Spacer(modifier = Modifier.size(32.dp))

        // Time
        Text(modifier = Modifier.wrapContentWidth(),
            textAlign = TextAlign.Center,
            text = "${playingTimeState.value.toInt().timeStampToDuration()} / ${duration.timeStampToDuration()}",
            color = MaterialTheme.colors.onSurface)

        // Audio Slider
        Slider(value = playingTimeState.value,
            onValueChange = {
                playingTimeState.value = it
                onSliderValueChanged(it)
            },
            valueRange = 0f..duration.toFloat(),
            onValueChangeFinished = {
                mediaPlayer.seekMediaPlayer((playingTimeState.value * 1000).toInt())
            },
            steps = 1000,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colors.secondary,
                activeTickColor = MaterialTheme.colors.secondary,
                inactiveTickColor = Color.Gray,
            )
        )

        Spacer(modifier = Modifier.size(32.dp))

        // Rating
        RatingStars(modifier = Modifier.padding(8.dp),
            rating = rating,
            starSize = 64,
            onStarClicked = { index ->
                onStarClicked(index + 1)
            })

    }
}

/*
@Preview
@Composable
fun AudioDetailItemPreview() {

    class MediaPlayerControllerForPreview: IMediaPlayerController {
        override fun audioSelected(url: String) {}
        override fun initializeMediaPlayer(url: String) {}
        override fun startMediaPlayer() {}
        override fun pauseMediaPlayer() {}
        override fun releaseMediaPlayer() {}
        override fun seekMediaPlayer(newPosition: Int) {}
    }

    return AudioDetailItem(
        audio = AudioEntry(
            uuid = "uuid1",
            title = "Oceansound",
            audio = "https://nomad5.com/data/skoove/Oceansound.mp3",
            cover = "https://nomad5.com/data/skoove/Oceansound.png",
            totalDurationMs = 14448
        ),
        mediaPlayer = MediaPlayerControllerForPreview(),
        isAudioPlaying = false,
        isFavorite = false,
        playingTime = 0f,
        duration = 100,
        rating = 2,
        onStarClicked = {},
        onSliderValueChanged = {},
        onFavoriteClicked = {}
    )
}
 */

@Preview
@Composable
fun AudioDetailItemPreview() {

    Slider(value = 0f,
        onValueChange = {

        },
        valueRange = 0f..20f,
        onValueChangeFinished = {

        },
        steps = 1000,
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colors.secondary,
            activeTickColor = MaterialTheme.colors.secondary,
            inactiveTickColor = Color.Gray,
        )
    )
}

