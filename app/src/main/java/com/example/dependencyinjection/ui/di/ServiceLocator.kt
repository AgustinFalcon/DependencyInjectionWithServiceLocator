package com.example.dependencyinjection.ui.di

import android.content.Context
import androidx.room.Room
import com.example.dependencyinjection.data.local.CharacterDBMapper
import com.example.dependencyinjection.data.local.entities.CharacterDao
import com.example.dependencyinjection.data.local.room.HelperDataBase
import com.example.dependencyinjection.data.remote.CharacterNetMapper
import com.example.dependencyinjection.data.remote.retrofit.ApiService
import com.example.dependencyinjection.repository.Character
import com.example.dependencyinjection.repository.CharacterRepository
import com.example.dependencyinjection.repository.CharacterRepositoryImpl


object ServiceLocator {

    private var dataBase: HelperDataBase? = null
    @Volatile
    var characterRepository: CharacterRepository? = null


    fun createDataBase(context: Context): HelperDataBase {
        val result = Room.databaseBuilder(
            context,
            HelperDataBase::class.java,
            HelperDataBase.DATABASE_NAME
        ).build()

        dataBase = result
        return result
    }

    fun provideCharacterRepository(context: Context): CharacterRepository {
        synchronized(this) {
            return characterRepository ?: CharacterRepositoryImpl(provideCharacterDao(context), provideApiService(), provideMapperBD(), provideMapperNet())
        }
    }

    private fun provideCharacterDao(context: Context): CharacterDao = createDataBase(context).characterDao()
    private fun provideApiService(): ApiService = ApiService.createApiService()
    private fun provideMapperNet(): CharacterNetMapper =  CharacterNetMapper()
    private fun provideMapperBD(): CharacterDBMapper = CharacterDBMapper()
}