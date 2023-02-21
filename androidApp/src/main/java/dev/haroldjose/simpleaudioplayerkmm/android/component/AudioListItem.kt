package dev.haroldjose.simpleaudioplayerkmm.android.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import dev.haroldjose.simpleaudioplayerkmm.android.R
import dev.haroldjose.simpleaudioplayerkmm.domain.model.AudioEntry

/**
 * Audio item for the overview list
 */
@Composable
fun AudioListItem(
    audio: AudioEntry,
    rating: Int = 3,
    isFavorite: Boolean = false,
    onFavoriteClicked: (newState: Boolean) -> Unit,
    onItemClicked: () -> Unit
) {


    Column(modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .border(1.dp, MaterialTheme.colors.onBackground)
        .background(MaterialTheme.colors.surface)
        .clickable {
            onItemClicked()
        }
    ) {

        Box {

            // Cover image
            CoilImage(imageModel = audio.cover,
                contentDescription = stringResource(id = R.string.contentDescription_audio_cover),
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = MaterialTheme.colors.surface
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(3f / 2f)
                    .fillMaxWidth())

            // Rating element
            RatingStars(modifier = Modifier.padding(8.dp), rating, onStarClicked = {})
        }

        // Title and favorite section
        Row(modifier = Modifier
            .fillMaxWidth(0.65f)
            .align(Alignment.End)
            .padding(horizontal = 8.dp)
            .heightIn(64.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Title
            Text(modifier = Modifier
                .wrapContentWidth(),
                textAlign = TextAlign.Center,
                text = audio.title.toString(),
                color = MaterialTheme.colors.onSurface)

            // Favorite Heart
            FavoriteElement(modifier = Modifier,
                favoriteState = isFavorite,
                onClick = { onFavoriteClicked(it) })
        }
    }
}

@Preview
@Composable
fun AudioListItemPreview() {

    Column() {
        AudioListItem(
            audio = AudioEntry(
                title = "Oceansound",
                audio = "https://nomad5.com/data/skoove/Oceansound.mp3",
                cover = "https://nomad5.com/data/skoove/Oceansound.png",
                totalDurationMs = 14448
            ),
            rating = 1,
            isFavorite = true,
            onFavoriteClicked = {},
            onItemClicked = {}
        )
    }
}