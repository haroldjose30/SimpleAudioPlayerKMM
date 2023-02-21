package dev.haroldjose.simpleaudioplayerkmm.android.ui.audiolistpage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.haroldjose.simpleaudioplayerkmm.android.R
import dev.haroldjose.simpleaudioplayerkmm.android.utils.ResourcesProvider
import dev.haroldjose.simpleaudioplayerkmm.domain.model.AudioEntry
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.GetAllAudioUseCase
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.SetFavoriteAudioUseCase
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.UpdateAudioUseCase

class AudioListPageViewModel(
    private val getAllAudioUseCase: GetAllAudioUseCase,
    private val setFavoriteAudioUseCase: SetFavoriteAudioUseCase,
    private val resourcesProvider: ResourcesProvider,
    //FIXME: DI with Error
    //private val updateAudioUseCase: UpdateAudioUseCase,
): ViewModel() {


    var pageState: AudioListPageState by mutableStateOf(AudioListPageState.Empty())

    suspend fun loadData(){

        try{
            pageState = AudioListPageState.Loading()
            val audioList = getAllAudioUseCase.execute()
            pageState = AudioListPageState.Success(audioList)
        }
        catch(e:Exception){
            e.printStackTrace()
            val defaultMessage = resourcesProvider.getString(R.string.something_went_wrong)
            pageState = AudioListPageState.Failure("$defaultMessage - ${e.message}")
        }
    }

    fun isLoading(): Boolean {
        return pageState is AudioListPageState.Loading
    }

    suspend fun onFavoriteClicked(
        audio: AudioEntry,
        isFavorite: Boolean
    ) {

        try{
            pageState = AudioListPageState.Loading()
            var audioMuttable = audio.copy()
            audioMuttable.isFavorite = isFavorite
            setFavoriteAudioUseCase.execute(audioMuttable)
            loadData()
        }
        catch(e:Exception){
            e.printStackTrace()
            val defaultMessage = resourcesProvider.getString(R.string.something_went_wrong)
            pageState = AudioListPageState.Failure("$defaultMessage - ${e.message}")
        }
    }

    suspend fun onStarClicked(
        audio: AudioEntry,
        index: Int
    ) {

        try{
            var audioMuttable = audio.copy()
            audioMuttable.rating = index
            //updateAudioUseCase.execute(audioMuttable)
        }
        catch(e:Exception){
            e.printStackTrace()
            val defaultMessage = resourcesProvider.getString(R.string.something_went_wrong)
            pageState = AudioListPageState.Failure("$defaultMessage - ${e.message}")
        }
    }
}

sealed class AudioListPageState {
    class Empty: AudioListPageState()
    class Loading: AudioListPageState()
    class Success(val data: List<AudioEntry>): AudioListPageState()
    class Failure(val error: String) : AudioListPageState()
}