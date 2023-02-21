package dev.haroldjose.simpleaudioplayerkmm.data.di

import dev.haroldjose.simpleaudioplayerkmm.data.repository.AudioInMemoryRepository
import dev.haroldjose.simpleaudioplayerkmm.data.repository.IAudioRepository
import dev.haroldjose.simpleaudioplayerkmm.data.repository.AudioRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val dataModules = module {

    singleOf(::AudioInMemoryRepository) bind IAudioRepository::class
    //singleOf(::AudioRepository) bind IAudioRepository::class
}