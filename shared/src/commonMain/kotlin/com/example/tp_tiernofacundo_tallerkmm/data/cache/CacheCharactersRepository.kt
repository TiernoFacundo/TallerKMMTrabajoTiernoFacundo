package com.example.tp_tiernofacundo_tallerkmm.data.cache

import com.example.tp_tiernofacundo_tallerkmm.data.cache.LocalCharactersDataSource
import com.example.tp_tiernofacundo_tallerkmm.domain.Character
import com.example.tp_tiernofacundo_tallerkmm.domain.CharactersRepository

class CacheCharactersRepository(
    private val remote: CharactersRepository,
    private val local: LocalCharactersDataSource
) : CharactersRepository {

    override suspend fun getCharacters(timestamp: Long, md5: String): List<Character> {
        return try {
            val remoteData = remote.getCharacters(timestamp, md5)
            local.saveCharacters(remoteData)
            remoteData
        } catch (e: Exception) {
            // Fallback a cache local
            local.getAllCharacters()
        }
    }
}