package com.example.tp_tiernofacundo_tallerkmm.util

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}