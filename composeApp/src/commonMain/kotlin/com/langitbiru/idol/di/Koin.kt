package com.langitbiru.idol.di

import com.langitbiru.idol.screen.Transition.TransitionViewModel
import com.langitbiru.idol.screen.home.HomeViewModel
import com.langitbiru.idol.screen.kabesha.KabeshaViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

val homeModule: Module = module {
    single { HomeViewModel() }
    single { KabeshaViewModel() }
    single { TransitionViewModel() }
}

fun initKoin() {
    startKoin { modules(homeModule) }
}