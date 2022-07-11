package com.example.dependencyinjection.repository

import android.util.Log
import com.example.dependencyinjection.data.local.CharacterDBMapper
import com.example.dependencyinjection.data.local.entities.CharacterDao
import com.example.dependencyinjection.data.remote.CharacterNetMapper
import com.example.dependencyinjection.data.remote.NetworkResult
import com.example.dependencyinjection.data.remote.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception


class CharacterRepositoryImpl(
    private val characterDao: CharacterDao,
    private val apiService: ApiService,
    private val characterDBMapper: CharacterDBMapper,
    private val characterNetMapper: CharacterNetMapper
) : CharacterRepository {

    override suspend fun getCharacters(): NetworkResult<List<Character>> = withContext(Dispatchers.IO) {

        try {
            if (characterDao.getAllCharacters().isNullOrEmpty()) {
                val remoteCharacter = apiService.getCharacters()
                Log.d(TAG, "Hello. API Call with ${remoteCharacter.code()} @ URL ${remoteCharacter.raw().request.url}")

                characterNetMapper.mapFromEntityList(remoteCharacter.body()?.results!!).forEach {
                    characterDao.insert(characterDBMapper.mapToEntity(it))
                }
                val cacheCharacter = characterDao.getAllCharacters()
                return@withContext (NetworkResult.Success(characterDBMapper.mapFromEntityList(cacheCharacter)))

            } else {
                val cacheCharacter = characterDao.getAllCharacters()
                return@withContext (NetworkResult.Success(characterDBMapper.mapFromEntityList(cacheCharacter)))
            }

        } catch (e: Exception) {
            return@withContext (NetworkResult.Error(e))
        }
    }

    companion object {
        private const val TAG = "CharacterRepositoryImpl"
    }
}