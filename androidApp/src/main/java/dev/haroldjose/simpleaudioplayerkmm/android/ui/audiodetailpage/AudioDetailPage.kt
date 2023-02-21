package dev.haroldjose.simpleaudioplayerkmm.android.ui.audiodetailpage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import dev.haroldjose.simpleaudioplayerkmm.android.R
import dev.haroldjose.simpleaudioplayerkmm.android.component.AudioDetailItem
import dev.haroldjose.simpleaudioplayerkmm.android.component.AudioListItem
import dev.haroldjose.simpleaudioplayerkmm.android.ui.MainActions
import dev.haroldjose.simpleaudioplayerkmm.android.utils.ResourcesProvider
import dev.haroldjose.simpleaudioplayerkmm.data.repository.local.IAudioLocalRepository
import dev.haroldjose.simpleaudioplayerkmm.data.response.AudioEntryDTO
import dev.haroldjose.simpleaudioplayerkmm.domain.model.AudioEntry
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.GetAllAudioUseCase
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.GetAudioByIdUseCase
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.SetFavoriteAudioUseCase
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.UpdateAudioUseCase
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun AudioDetailPage(
    viewModel: AudioDetailPageViewModel = getViewModel(),
    actions: MainActions,
    uuid: String
) {
    LaunchedEffect(key1 = "AudioDetailPage"){
        viewModel.loadData(uuid)
    }

    when (viewModel.pageState) {
        is AudioDetailPageState.Loading -> LoadingView()
        is AudioDetailPageState.Failure -> FailureView(
            (viewModel.pageState as AudioDetailPageState.Failure).error
        )
        is AudioDetailPageState.Success -> SuccessView(
            (viewModel.pageState as AudioDetailPageState.Success).data,
            viewModel,
            actions,
        )
    }
}

@Composable
private fun LoadingView(){
    Row {
        Spacer(Modifier.weight(1f))
        CircularProgressIndicator()
        Spacer(Modifier.weight(1f))
    }
}

@Composable
private fun FailureView(message: String){
    //TODO: Improve UI
    Text(text = message)
}
@Composable
private fun SuccessView(
    audio: AudioEntry,
    viewModel: AudioDetailPageViewModel,
    actions: MainActions,
) {

    //region STATE
    val coroutineScope = rememberCoroutineScope()
    //endregion

    AudioDetailItem(
        audio = audio,
        mediaPlayer = viewModel,
        isAudioPlaying = false,
        isFavorite = audio.isFavorite,
        playingTime = 0f,
        duration = audio.totalDurationSeconds,
        rating = 2,
        onStarClicked = {
            coroutineScope.launch {
                viewModel.onStarClicked(audio,it)
            }
        },
        onSliderValueChanged = {
            viewModel.onSliderValueChanged(audio,it)
        },
        onFavoriteClicked = {
            coroutineScope.launch {
                viewModel.onFavoriteClicked(audio,it)
            }
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AudioDetailPagePreview() {

    //TODO: improve a way to mock using DI to handle preview
    var navController = rememberNavController()
    val actions = MainActions(navController)
    val resourcesProvider = ResourcesProvider(LocalContext.current)
    val repository = PreviewRepo()
    val getAudioByIdUseCase = GetAudioByIdUseCase(repository)
    val updateAudioUseCase = UpdateAudioUseCase(repository)
    val setFavoriteAudioUseCase = SetFavoriteAudioUseCase(repository)


    val viewModel = AudioDetailPageViewModel(
        getAudioByIdUseCase,
        setFavoriteAudioUseCase,
        resourcesProvider,
        //updateAudioUseCase,
        )

    val audio = AudioEntry(
        uuid = "uuid1",
        title = "Oceansound",
        audio = "https://nomad5.com/data/skoove/Oceansound.mp3",
        cover = "https://nomad5.com/data/skoove/Oceansound.png",
        totalDurationMs = 14448,
        isFavorite = true
    )

    viewModel.pageState = AudioDetailPageState.Success(audio)

    return AudioDetailPage(
        viewModel = viewModel,
        actions = actions,
        uuid = "uuid1",
    )
}

class PreviewRepo: IAudioLocalRepository {
    override suspend fun getAll(): List<AudioEntryDTO> { return arrayListOf() }
    override suspend fun getById(uuid: String): AudioEntryDTO? {  return null }
    override suspend fun updateAudio(audioEntryDTO: AudioEntryDTO) {}
}