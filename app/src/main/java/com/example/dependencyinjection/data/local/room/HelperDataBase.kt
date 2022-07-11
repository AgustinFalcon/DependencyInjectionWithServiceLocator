package com.example.dependencyinjection.data.local.room

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import com.example.dependencyinjection.data.local.entities.CharacterDao
import com.example.dependencyinjection.data.local.entities.CharacterEntity


@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class HelperDataBase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        const val DATABASE_NAME: String = "RickAndMorty_DB"
    }
}