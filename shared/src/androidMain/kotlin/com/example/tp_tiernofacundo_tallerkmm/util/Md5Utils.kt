package com.example.tp_tiernofacundo_tallerkmm.util

import java.security.MessageDigest

actual fun md5(string: String): String {
    val digest = MessageDigest.getInstance("MD5")
    val bytes = digest.digest(string.toByteArray())
    return bytes.joinToString("") { "%02x".format(it) }
}