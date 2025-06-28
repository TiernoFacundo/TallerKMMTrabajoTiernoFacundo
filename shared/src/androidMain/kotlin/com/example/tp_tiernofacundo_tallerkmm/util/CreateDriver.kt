package com.example.tp_tiernofacundo_tallerkmm.util

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.tp_tiernofacundo_tallerkmm.cache.AppDatabase


actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            AppDatabase.Schema, context,
            "Characters.db")
    }
}