package dev.haroldjose.simpleaudioplayerkmm.android.ui.audiodetailpage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.haroldjose.simpleaudioplayerkmm.android.R
import dev.haroldjose.simpleaudioplayerkmm.android.ui.audiolistpage.AudioListPageState
import dev.haroldjose.simpleaudioplayerkmm.android.ui.mediaplayer.MediaPlayerController
import dev.haroldjose.simpleaudioplayerkmm.android.utils.ResourcesProvider
import dev.haroldjose.simpleaudioplayerkmm.domain.model.AudioEntry
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.GetAllAudioUseCase
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.GetAudioByIdUseCase
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.SetFavoriteAudioUseCase
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.UpdateAudioUseCase

class AudioDetailPageViewModel(
    private val getAudioByIdUseCase: GetAudioByIdUseCase,
    private val resourcesProvider: ResourcesProvider,
    //FIXME: DI With error
    //private val setFavoriteAudioUseCase: SetFavoriteAudioUseCase,
    //private val updateAudioUseCase: UpdateAudioUseCase,

): MediaPlayerController() {

    var pageState: AudioDetailPageState by mutableStateOf(AudioDetailPageState.Loading())

    suspend fun loadData(uuid:String){

        try{
            pageState = AudioDetailPageState.Loading()

            val audio = getAudioByIdUseCase.execute(uuid)

            audio?.let {
                pageState = AudioDetailPageState.Success(it)
            } ?: run {
                showGenericError(null)
            }
        }
        catch(e:Exception){
            showGenericError(e)
        }
    }

    fun isLoading(): Boolean {
        return pageState is AudioDetailPageState.Loading
    }

    private fun showGenericError(e:Exception?){

        val defaultMessage = resourcesProvider.getString(R.string.something_went_wrong)
        pageState = AudioDetailPageState.Failure("$defaultMessage ${e?.message}")
        e?.printStackTrace()
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
            pageState = AudioDetailPageState.Failure("$defaultMessage - ${e.message}")
        }
    }

    fun onSliderValueChanged(audio: AudioEntry, slideValue: Float) {

        this.seekMediaPlayer(slideValue.toInt())
    }

    suspend fun onFavoriteClicked(
        audio: AudioEntry,
        isFavorite: Boolean
    ) {

        try{
            var audioMuttable = audio.copy()
            audioMuttable.isFavorite = isFavorite
            //setFavoriteAudioUseCase.execute(audioMuttable)
        }
        catch(e:Exception){
            e.printStackTrace()
            val defaultMessage = resourcesProvider.getString(R.string.something_went_wrong)
            pageState = AudioDetailPageState.Failure("$defaultMessage - ${e.message}")
        }
    }
}

sealed class AudioDetailPageState {
    class Loading: AudioDetailPageState()
    class Success(val data: AudioEntry): AudioDetailPageState()
    class Failure(val error: String) : AudioDetailPageState()
}