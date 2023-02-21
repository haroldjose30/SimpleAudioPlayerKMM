package dev.haroldjose.simpleaudioplayerkmm.domain.di



import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.GetAllAudioUseCase
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.GetAudioByIdUseCase
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.SetFavoriteAudioUseCase
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.UpdateAudioUseCase
import org.koin.core.Koin

val Koin.getAllAudioUseCase: GetAllAudioUseCase
    get() = get()

val Koin.getAudioByIdUseCase: GetAudioByIdUseCase
    get() = get()

val Koin.setFavoriteAudioUseCase: SetFavoriteAudioUseCase
    get() = get()

val Koin.updateAudioUseCase: UpdateAudioUseCase
    get() = get()