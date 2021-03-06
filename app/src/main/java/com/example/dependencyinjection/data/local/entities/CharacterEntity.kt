package com.example.dependencyinjection.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rickandmorty_entity")
data class CharacterEntity (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name:String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "species")
    val species: String,

    @ColumnInfo(name = "gender")
    val gender: String,

    @ColumnInfo(name = "image")
    val image: String
)