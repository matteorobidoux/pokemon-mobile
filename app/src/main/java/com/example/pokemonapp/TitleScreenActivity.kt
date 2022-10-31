package com.example.pokemonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemonapp.databinding.TitleScreenBinding

class TitleScreenActivity : AppCompatActivity() {

    private lateinit var binding: TitleScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TitleScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}