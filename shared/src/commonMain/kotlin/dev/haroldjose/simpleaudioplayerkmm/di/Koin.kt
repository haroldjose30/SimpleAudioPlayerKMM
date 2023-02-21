package dev.haroldjose.simpleaudioplayerkmm.di

import dev.haroldjose.simpleaudioplayerkmm.data.di.dataModules
import dev.haroldjose.simpleaudioplayerkmm.domain.di.domainModules
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

// called by Android
fun initKoin(
    appDeclaration: KoinAppDeclaration = {}
) = startKoin {

    appDeclaration()
    modules(
        sharedModules()
    )
}

// Koin utilities for iOS injection
fun KoinApplication.Companion.start(): KoinApplication = initKoin {
    modules(
        sharedModules()
    )
}

fun sharedModules(): List<Module> {

    return listOf(dataModules, domainModules)
}