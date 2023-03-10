package dev.haroldjose.simpleaudioplayerkmm.android.ui.audiolistpage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.haroldjose.simpleaudioplayerkmm.android.R
import dev.haroldjose.simpleaudioplayerkmm.android.component.AudioListItem
import dev.haroldjose.simpleaudioplayerkmm.android.ui.Destinations
import dev.haroldjose.simpleaudioplayerkmm.android.ui.MainActions
import dev.haroldjose.simpleaudioplayerkmm.android.ui.audiodetailpage.PreviewRepo
import dev.haroldjose.simpleaudioplayerkmm.android.utils.ResourcesProvider
import dev.haroldjose.simpleaudioplayerkmm.data.repository.local.IAudioLocalRepository
import dev.haroldjose.simpleaudioplayerkmm.data.response.AudioEntryDTO
import dev.haroldjose.simpleaudioplayerkmm.domain.model.AudioEntry
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.GetAllAudioUseCase
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.SetFavoriteAudioUseCase
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.UpdateAudioUseCase
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AudioListPage(
    viewModel: AudioListPageViewModel = getViewModel(),
    actions: MainActions,
) {
    //region STATE
    val coroutineScope = rememberCoroutineScope()
    val pullRefreshState = rememberPullRefreshState(viewModel.isLoading(), {
        coroutineScope.launch {
            viewModel.loadData()
        }
    })
    val listState = rememberLazyListState()
    //endregion

    LaunchedEffect(key1 = "AudioListPage"){
        viewModel.loadData()
    }

    when (viewModel.pageState) {
        is AudioListPageState.Empty -> EmptyView()
        is AudioListPageState.Loading -> LoadingView(
            viewModel.isLoading(),
            pullRefreshState
        )
        is AudioListPageState.Failure -> FailureView(
            (viewModel.pageState as AudioListPageState.Failure).error
        )
        is AudioListPageState.Success -> SuccessView(
            listState,
            pullRefreshState,
            (viewModel.pageState as AudioListPageState.Success).data,
            viewModel,
            actions,
        )
    }
}

@Composable
private fun EmptyView(){
    //TODO: Improve UI for EmptyState
    Text(text = "Empty...")
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun LoadingView(
    isLoading: Boolean,
    pullRefreshState: PullRefreshState
){
    Row {
        Spacer(Modifier.weight(1f))
        PullRefreshIndicator(isLoading, pullRefreshState)
        Spacer(Modifier.weight(1f))
    }
}

@Composable
private fun FailureView(message: String){
    //TODO: Improve UI for Error
    Text(text = message)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SuccessView(
    listState: LazyListState,
    pullRefreshState: PullRefreshState,
    audioList: List<AudioEntry>,
    viewModel: AudioListPageViewModel,
    actions: MainActions,
) {
    val coroutineScope = rememberCoroutineScope()
    Column(Modifier.pullRefresh(pullRefreshState)) {

        Row {
            Spacer(Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.audio_list_page_title),
                style = TextStyle(fontSize = 24.sp)
            )
            Spacer(Modifier.weight(1f))
        }

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(audioList) { audioEntry ->

                AudioListItem(
                    audio = audioEntry,
                    onFavoriteClicked = {
                        coroutineScope.launch {
                            viewModel.onFavoriteClicked(audioEntry,it)
                        }
                    },
                    onItemClicked = {
                        actions.navigateToAudioDetail(audioEntry)
                    },
                    onStarClicked = {
                        coroutineScope.launch {
                            viewModel.onStarClicked(audioEntry,it)
                        }
                    }
                )
            }
        }
    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AudioListPagePreview() {

    //TODO: improve a way to mock using DI to handle preview
    var navController = rememberNavController()
    val actions = MainActions(navController)
    val resourcesProvider = ResourcesProvider(LocalContext.current)
    val repository = PreviewRepo()
    val updateAudioUseCase = UpdateAudioUseCase(repository = repository)
    val getAllAudioUseCase = GetAllAudioUseCase(repository = repository)
    val setFavoriteAudioUseCase = SetFavoriteAudioUseCase(repository = repository)
    val viewModel = AudioListPageViewModel(
        getAllAudioUseCase,
        setFavoriteAudioUseCase,
        resourcesProvider,
        //updateAudioUseCase,
    )

    val datasource = mutableListOf<AudioEntry>(
        AudioEntry(
            uuid = "uuid1",
            title = "Oceansound",
            audio = "https://nomad5.com/data/skoove/Oceansound.mp3",
            cover = "https://nomad5.com/data/skoove/Oceansound.png",
            totalDurationMs = 14448,
            isFavorite = true
        ),
        AudioEntry(
            uuid = "uuid2",
            title = "Nightlife",
            audio = "https://nomad5.com/data/skoove/Nightlife.mp3",
            cover = "https://nomad5.com/data/skoove/Nightlife.png",
            totalDurationMs = 15696
        ),
        AudioEntry(
            uuid = "uuid3",
            title = "Waking Me",
            audio = "https://nomad5.com/data/skoove/Waking_Me.mp3",
            cover = "https://nomad5.com/data/skoove/Waking_Me.png",
            totalDurationMs = 13776
        )
    )

    viewModel.pageState = AudioListPageState.Success(datasource)

    return AudioListPage(
        viewModel = viewModel,
        actions = actions
    )
}