package com.example.dependencyinjection.data.remote

import com.example.dependencyinjection.data.Mapper
import com.example.dependencyinjection.data.remote.parsedata.RemoteCharacter
import com.example.dependencyinjection.repository.Character

class CharacterNetMapper: Mapper<RemoteCharacter, Character> {

    override fun mapFromEntity(entity: RemoteCharacter): Character {
        return Character(
            id = entity.id,
            name = entity.name,
            status = entity.status,
            species = entity.species,
            gender = entity.gender,
            image = entity.image
        )
    }

    override fun mapToEntity(domainModel: Character): RemoteCharacter {
        return RemoteCharacter(
            id = domainModel.id,
            name = domainModel.name,
            status = domainModel.status,
            species = domainModel.species,
            gender = domainModel.gender,
            image = domainModel.image
        )
    }

    fun mapFromEntityList(entites: List<RemoteCharacter>): List<Character> {
        return entites.map { mapFromEntity(it) }
    }
}