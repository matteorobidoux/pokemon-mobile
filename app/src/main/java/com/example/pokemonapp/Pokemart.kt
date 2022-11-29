package com.example.pokemonapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.PokemartBinding

class Pokemart: AppCompatActivity() {

    private lateinit var binding: PokemartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokemartBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}