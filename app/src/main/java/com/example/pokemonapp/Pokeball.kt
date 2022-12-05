package com.example.pokemonapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonapp.databinding.PokeballBinding

class Pokeball: AppCompatActivity() {
    private lateinit var binding: PokeballBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokeballBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.buyPokeballBtn.setOnClickListener {
//            changeActivityPokeball()
//        }

        binding.backPokeballBtn.setOnClickListener {
            changeActivityPokemart()
        }
    }
    private fun changeActivityPokemart(){
        var mainIntent = Intent(applicationContext, Pokemart::class.java)
        startActivity(mainIntent)
    }

}
