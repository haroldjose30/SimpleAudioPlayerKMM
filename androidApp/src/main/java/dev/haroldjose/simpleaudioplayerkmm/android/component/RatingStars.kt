package dev.haroldjose.simpleaudioplayerkmm.android.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.haroldjose.simpleaudioplayerkmm.android.R
import dev.haroldjose.simpleaudioplayerkmm.android.ui.MyApp

/**
 * Rating stars Component to show audio rating with filled stars
 */
@Composable
fun RatingStars(
    modifier: Modifier,
    rating: Int,
    ratingMax: Int = 5,
    starSize: Int = 24,
    onStarClicked: (index: Int) -> Unit
) {

    Row(modifier = modifier.testTag("RatingStars")) {
        repeat(ratingMax) { index ->
            Icon(painter = painterResource(id = if (index >= rating) R.drawable.ic_star else R.drawable.ic_star_filled),
                contentDescription = stringResource(id = R.string.contentDescription_audio_rating_start),
                modifier = Modifier
                    .size(starSize.dp)
                    .testTag("star")
                    .padding(horizontal = 1.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false)
                    ) {
                        onStarClicked(index)
                    }
            )
        }
    }
}

@Preview
@Composable
fun RatingStarsPreview() {

    Column() {
        RatingStars(
            modifier = Modifier,
            rating = 0,
            onStarClicked = { }
        )

        RatingStars(
            modifier = Modifier,
            rating = 1,
            starSize = 10,
            onStarClicked = { }
        )

        RatingStars(
            modifier = Modifier,
            rating = 2,
            onStarClicked = { }
        )

        RatingStars(
            modifier = Modifier,
            rating = 3,
            onStarClicked = { }
        )

        RatingStars(
            modifier = Modifier,
            rating = 4,
            onStarClicked = { }
        )

        RatingStars(
            modifier = Modifier,
            rating = 5,
            starSize = 30,
            onStarClicked = { }
        )
    }
}