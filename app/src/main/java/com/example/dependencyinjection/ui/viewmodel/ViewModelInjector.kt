package com.example.dependencyinjection.ui.viewmodel

import com.example.dependencyinjection.repository.CharacterRepository


object ViewModelInjector {

    fun provideViewModelFactory(repository: CharacterRepository): ViewModelFactory {
        return ViewModelFactory(repository)
    }

}