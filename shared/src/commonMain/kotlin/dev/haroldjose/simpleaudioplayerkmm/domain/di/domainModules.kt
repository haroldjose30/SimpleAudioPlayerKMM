package dev.haroldjose.simpleaudioplayerkmm.domain.di

import dev.haroldjose.simpleaudioplayerkmm.domain.usecase.audio.GetAllAudioUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val domainModules = module {

    factoryOf(::GetAllAudioUseCase)
}
