package com.example.tp_tiernofacundo_tallerkmm.android.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tp_tiernofacundo_tallerkmm.data.MarvelCharactersClient
import com.example.tp_tiernofacundo_tallerkmm.data.KtkorCharactersRepository
import com.example.tp_tiernofacundo_tallerkmm.data.CharactersService
import com.example.tp_tiernofacundo_tallerkmm.android.data.createHttpClient
import com.example.tp_tiernofacundo_tallerkmm.cache.AppDatabase
import com.example.tp_tiernofacundo_tallerkmm.data.cache.CacheCharactersRepository
import com.example.tp_tiernofacundo_tallerkmm.data.cache.LocalCharactersDataSource
import com.example.tp_tiernofacundo_tallerkmm.util.DatabaseDriverFactory


class CharactersViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val client = createHttpClient()
        val apiClient = MarvelCharactersClient(client)
        val remoteRepo = KtkorCharactersRepository(apiClient)

        // Crear base de datos local
        val driverFactory = DatabaseDriverFactory(context)
        val db = AppDatabase(driverFactory.createDriver())
        val localRepo = LocalCharactersDataSource(db)

        // CacheRepository
        val cacheRepo = CacheCharactersRepository(remoteRepo, localRepo)
        val service = CharactersService(cacheRepo)

        return CharactersViewModel(service) as T
    }
}