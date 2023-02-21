package dev.haroldjose.simpleaudioplayerkmm.android.ui

import android.annotation.SuppressLint
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.haroldjose.simpleaudioplayerkmm.android.ui.audiodetailpage.AudioDetailPage
import dev.haroldjose.simpleaudioplayerkmm.android.ui.audiolistpage.AudioListPage
import dev.haroldjose.simpleaudioplayerkmm.domain.model.AudioEntry

object Destinations {
    const val AUDIO_LIST_ROUTE = "audio_list"
    const val AUDIO_DETAIL_ROUTE = "audio_detail"

    object Arguments {
        const val UUID = "uuid"
    }
}

@Composable
fun MyNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.AUDIO_LIST_ROUTE
) {

    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

        composable(Destinations.AUDIO_LIST_ROUTE) {
            AudioListPage(actions = actions)
        }

        composable(Destinations.AUDIO_DETAIL_ROUTE,
            arguments = listOf(
                navArgument(Destinations.Arguments.UUID) {
                    nullable = true
                    type = NavType.StringType
                })) {


            it.arguments?.getString(Destinations.Arguments.UUID)?.let {
                uuid ->
                AudioDetailPage(
                    actions = actions,
                    uuid = uuid
                )
            }
        }

        composable(
            "${Destinations.AUDIO_DETAIL_ROUTE}/{${Destinations.Arguments.UUID}}",
            arguments = listOf(navArgument(Destinations.Arguments.UUID) { type = NavType.StringType })
        ) {  backStackEntry ->
                backStackEntry.arguments?.getString(Destinations.Arguments.UUID)?.let {

                    AudioDetailPage(
                        actions = actions,
                        uuid = it
                    )
                }
        }
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {

    val navigateToAudioDetail: (audio: AudioEntry) -> Unit = { audio ->
        navController.navigate("${Destinations.AUDIO_DETAIL_ROUTE}/${audio.uuid}")
    }
}

