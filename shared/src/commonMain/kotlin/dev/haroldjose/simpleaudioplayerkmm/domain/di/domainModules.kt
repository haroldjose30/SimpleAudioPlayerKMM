package dev.haroldjose.simpleaudioplayerkmm.domain.di

import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.GetAllAudioUseCase
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.GetAudioByIdUseCase
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.SetFavoriteAudioUseCase
import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.UpdateAudioUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val domainModules = module {

    factoryOf(::GetAudioByIdUseCase)
    factoryOf(::GetAllAudioUseCase)
    factoryOf(::SetFavoriteAudioUseCase)
    factoryOf(::UpdateAudioUseCase)
}
