package dev.haroldjose.simpleaudioplayerkmm.data.di

import dev.haroldjose.simpleaudioplayerkmm.data.repository.local.AudioInMemoryLocalRepository
import dev.haroldjose.simpleaudioplayerkmm.data.repository.local.IAudioLocalRepository
import dev.haroldjose.simpleaudioplayerkmm.data.repository.remote.AudioRemoteRepository
import dev.haroldjose.simpleaudioplayerkmm.data.repository.remote.IAudioRemoteRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val dataModules = module {

    singleOf(::AudioInMemoryLocalRepository) bind IAudioLocalRepository::class
    singleOf(::AudioRemoteRepository) bind IAudioRemoteRepository::class
}