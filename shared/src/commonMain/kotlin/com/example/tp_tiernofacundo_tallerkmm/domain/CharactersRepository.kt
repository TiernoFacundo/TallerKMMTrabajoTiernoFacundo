package com.example.tp_tiernofacundo_tallerkmm.domain

interface CharactersRepository {
    suspend fun getCharacters(timestamp: Long, md5: String): List<Character>
}