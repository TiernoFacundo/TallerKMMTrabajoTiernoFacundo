package com.example.tp_tiernofacundo_tallerkmm.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json

/*class MarvelCharactersClient(private val client: HttpClient) {

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun getAllCharacters(timestamp: Long, md5: String): CharactersResponse {
        val response: HttpResponse = client.get("v1/public/characters") {
            parameter("ts", timestamp)
            parameter("hash", md5)
        }

        val responseBody = response.bodyAsText()
        return json.decodeFromString(responseBody)
    }*/
class MarvelCharactersClient(private val client: HttpClient) {

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun getAllCharacters(timestamp: Long, md5: String): CharactersResponse {
        val response: HttpResponse = client.get("v1/images/search") {
            parameter("limit", 50)
        }

        val responseBody = response.bodyAsText()
        val cats = json.decodeFromString<List<CatApiResponse>>(responseBody)

        return CharactersResponse(
            characters = CharacterData(
                list = cats.mapIndexed { index, cat ->
                    CharacterResult(
                        id = index.toLong(),
                        name = "Cat #$index",
                        description = "Un Lindo Gatito Para usted profe 🐱",
                        thumbnail = Thumbnail(
                            path = cat.imageUrl.substringBeforeLast("."),
                            extension = cat.imageUrl.substringAfterLast(".")
                        )
                    )
                })
        )
    }

    @Serializable
    data class CatApiResponse(
        @SerialName("id") val id: String,
        @SerialName("url") val imageUrl: String
    )

    @Serializable
    data class CharactersResponse(
        @SerialName("data") val characters: CharacterData
    )

    @Serializable
    data class CharacterData(
        @SerialName("results") val list: List<CharacterResult>
    )

    @Serializable
    data class CharacterResult(
        @SerialName("id") val id: Long,
        @SerialName("name") val name: String,
        @SerialName("description") val description: String,
        @SerialName("thumbnail") val thumbnail: Thumbnail
    )

    @Serializable
    data class Thumbnail(
        @SerialName("path") val path: String,
        @SerialName("extension") val extension: String
    ) {
        fun toUrl(): String = "$path.$extension"
    }
}