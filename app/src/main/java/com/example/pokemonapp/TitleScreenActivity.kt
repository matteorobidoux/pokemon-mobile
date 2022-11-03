package com.example.pokemonapp

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.pokemonapp.databinding.TitleScreenBinding
import kotlinx.coroutines.delay

class TitleScreenActivity : AppCompatActivity() {

    private lateinit var binding: TitleScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TitleScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitleMusic()
    }

    private fun setTitleMusic(){
        var mediaPlayer = MediaPlayer.create(applicationContext, R.raw.title_screen_music)
        mediaPlayer.start()
    }
}