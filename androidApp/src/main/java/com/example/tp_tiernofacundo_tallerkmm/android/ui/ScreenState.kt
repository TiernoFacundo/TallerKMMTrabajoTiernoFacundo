package com.example.tp_tiernofacundo_tallerkmm.android.ui

import com.example.tp_tiernofacundo_tallerkmm.domain.Character

sealed class ScreenState {

    object Loading : ScreenState()

    class ShowCharacters(val list: List<Character>) : ScreenState()
}