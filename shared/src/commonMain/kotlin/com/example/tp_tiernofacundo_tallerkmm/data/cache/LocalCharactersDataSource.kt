package com.example.tp_tiernofacundo_tallerkmm.data.cache

import com.example.tp_tiernofacundo_tallerkmm.cache.AppDatabase
import com.example.tp_tiernofacundo_tallerkmm.domain.Character

class LocalCharactersDataSource(private val db: AppDatabase) {

    private val queries = db.charactersQueries

    suspend fun getAllCharacters(): List<Character> {
        return queries.selectAll()
            .executeAsList()
            .map {
                Character(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    thumbnailUrl = it.thumbnailUrl
                )
            }
    }

    suspend fun saveCharacters(characters: List<Character>) {
        queries.transaction {
            queries.deleteAll()
            characters.forEach {
                queries.insertCharacter(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    thumbnailUrl = it.thumbnailUrl
                )
            }
        }
    }
}