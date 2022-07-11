package com.example.dependencyinjection

import android.app.Application
import com.example.dependencyinjection.repository.CharacterRepository
import com.example.dependencyinjection.ui.di.ServiceLocator

class App : Application() {
    val characterRepository: CharacterRepository get() = ServiceLocator.provideCharacterRepository(this)
}