package com.example.dependencyinjection.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dependencyinjection.data.remote.NetworkResult
import com.example.dependencyinjection.repository.CharacterRepository
import java.lang.Exception
import com.example.dependencyinjection.repository.Character
import kotlinx.coroutines.launch


class CharacterViewModel (private val repository: CharacterRepository): ViewModel() {

    private val _characterViewState = MutableLiveData<CharacterViewStates>()
    val characterViewState: LiveData<CharacterViewStates> get() = _characterViewState

    fun getCharacters() {
        viewModelScope.launch {
            when (val result = repository.getCharacters()) {
                is NetworkResult.Success -> {
                    _characterViewState.postValue(CharacterViewStates.Success(result.data))
                }
                is NetworkResult.Error -> {
                    _characterViewState.postValue(CharacterViewStates.Error(result.error))
                }
                NetworkResult.Loading -> {
                    _characterViewState.postValue(CharacterViewStates.Loading)
                }
                else -> {
                    Log.d("CharacterViewModel", "Error in the call http")
                }
            }
        }
    }

}

sealed class CharacterViewStates {
    data class Success(val data: List<Character>): CharacterViewStates()
    data class Error(val error: Exception): CharacterViewStates()
    object Loading: CharacterViewStates()
}