package dev.haroldjose.simpleaudioplayerkmm.android.di

import dev.haroldjose.simpleaudioplayerkmm.android.ui.audiodetailpage.AudioDetailPageViewModel
import dev.haroldjose.simpleaudioplayerkmm.android.ui.audiolistpage.AudioListPageViewModel
import dev.haroldjose.simpleaudioplayerkmm.android.utils.ResourcesProvider
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val androidModules = module {

    singleOf(::ResourcesProvider)
    viewModelOf(::AudioListPageViewModel)
    viewModelOf(::AudioDetailPageViewModel)
}