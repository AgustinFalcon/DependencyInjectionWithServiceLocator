package com.example.dependencyinjection.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dependencyinjection.repository.CharacterRepository


class ViewModelFactory(private val characterRepository: CharacterRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterViewModel(characterRepository) as T
    }
}