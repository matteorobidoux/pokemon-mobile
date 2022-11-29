package com.example.pokemonapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.TeamActivityBinding

class TeamActivity : AppCompatActivity(){
    private lateinit var binding: TeamActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TeamActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}