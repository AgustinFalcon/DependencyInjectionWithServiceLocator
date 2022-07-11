package com.example.dependencyinjection.repository

import com.example.dependencyinjection.data.remote.NetworkResult


interface CharacterRepository {
    suspend fun getCharacters(): NetworkResult<List<Character>>
}