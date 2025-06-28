package com.example.tp_tiernofacundo_tallerkmm.android.data

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.http.URLProtocol
import okhttp3.OkHttpClient

fun createHttpClient(): HttpClient {
    return HttpClient(OkHttp) {
        engine {
            preconfigured = OkHttpClient.Builder()
                .addInterceptor(PublicKeyInterceptor())
                .build()
        }

        install(DefaultRequest) {
            url {
                /*host = "gateway.marvel.com"*/
                host = "api.thecatapi.com"
                protocol = URLProtocol.HTTPS
            }
        }
    }
}