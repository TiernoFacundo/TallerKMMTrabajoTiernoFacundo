package com.example.tp_tiernofacundo_tallerkmm.android


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp_tiernofacundo_tallerkmm.domain.Character
import com.example.tp_tiernofacundo_tallerkmm.android.databinding.ActivityMainBinding
import com.example.tp_tiernofacundo_tallerkmm.android.ui.CharactersAdapter
import com.example.tp_tiernofacundo_tallerkmm.android.ui.CharactersViewModel
import com.example.tp_tiernofacundo_tallerkmm.android.ui.CharactersViewModelFactory
import com.example.tp_tiernofacundo_tallerkmm.android.ui.ScreenState
import com.example.tp_tiernofacundo_tallerkmm.android.ui.VerticalSpaceItemDecoration
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup del listado
        charactersAdapter = CharactersAdapter()
        val verticalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        with(binding.charactersList) {
            this.adapter = charactersAdapter
            this.layoutManager = verticalLayoutManager
            this.addItemDecoration(VerticalSpaceItemDecoration(16))
        }

        // Listen to Retrofit response
        val viewModel =
            ViewModelProvider(this, CharactersViewModelFactory(this))[CharactersViewModel::class.java]
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.screenState.collect {
                    when (it) {
                        ScreenState.Loading -> showLoading()
                        is ScreenState.ShowCharacters -> showCharacters(it.list)
                    }
                }
            }
        }
    }

    private fun showLoading() {

    }

    private fun showCharacters(list: List<Character>) {
        charactersAdapter.submitList(list)
    }
}